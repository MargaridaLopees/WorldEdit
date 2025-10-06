# Code Smells 

# 1.Duplicated code

**Code location:**  worldedit-core/src/main/java/com.sk89q/util/command/util/SuperPickaxeCommands

**Description**
 The method single and area in the class SuperPickaxeCommands have some identical lines: 
 session.setSuperPickaxe(new SinglePickaxe());
 session.enableSuperPickAxe();
 player.printInfo();
 These identical lines makes the class longer and more difficult to read and find some specific code lines.

```java
@CommandPermissions("worldedit.superpickaxe")
public void single(Player player, LocalSession session) throws WorldEditException {
session.setSuperPickaxe(new SinglePickaxe());
session.enableSuperPickAxe();
player.printInfo(TranslatableComponent.of("worldedit.tool.superpickaxe.mode.single"));
}
```
```java
@CommandPermissions("worldedit.superpickaxe.area")
public void area(Player player, LocalSession session,
@Arg(desc = "The range of the area pickaxe")
int range) throws WorldEditException {

        LocalConfiguration config = we.getConfiguration();

        if (range > config.maxSuperPickaxeSize) {
            player.printError(TranslatableComponent.of("worldedit.tool.superpickaxe.max-range", TextComponent.of(config.maxSuperPickaxeSize)));
            return;
        }

        session.setSuperPickaxe(new AreaPickaxe(range));
        session.enableSuperPickAxe();
        player.printInfo(TranslatableComponent.of("worldedit.tool.superpickaxe.mode.area"));
    }
 ```

 **Proposal:**
 I would propose extracting the identical code lines to an auxiliary method.


# 2.Long method 

**Code location:** worldedit-cli/src/main/java/com.sk89q/schematic/CLIWorldEdit

The method setupRegistries() is too long with 57 lines and has some repeated code like excessive use of for.

 ```java
public void setupRegistries() {
// Blocks
for (Map.Entry<String, FileRegistries.BlockManifest> manifestEntry : fileRegistries.getDataFile().blocks.entrySet()) {
if (BlockType.REGISTRY.get(manifestEntry.getKey()) == null) {
BlockType.REGISTRY.register(manifestEntry.getKey(), new BlockType(manifestEntry.getKey(), input -> {
ParserContext context = new ParserContext();
context.setPreferringWildcard(true);
context.setTryLegacy(false);
context.setRestricted(false);
try {
FuzzyBlockState state = (FuzzyBlockState) WorldEdit.getInstance().getBlockFactory().parseFromInput(
manifestEntry.getValue().defaultstate,
context
).toImmutableState();
BlockState defaultState = input.getBlockType().getAllStates().get(0);
for (Map.Entry<Property<?>, Object> propertyObjectEntry : state.getStates().entrySet()) {
@SuppressWarnings("unchecked")
Property<Object> prop = (Property<Object>) propertyObjectEntry.getKey();
defaultState = defaultState.with(prop, propertyObjectEntry.getValue());
}
return defaultState;
} catch (InputParseException e) {
LOGGER.warn("Error loading block state for " + manifestEntry.getKey(), e);
return input;
}
}));
}
}
// Items
for (String name : fileRegistries.getDataFile().items) {
if (ItemType.REGISTRY.get(name) == null) {
ItemType.REGISTRY.register(name, new ItemType(name));
}
}
// Entities
for (String name : fileRegistries.getDataFile().entities) {
if (EntityType.REGISTRY.get(name) == null) {
EntityType.REGISTRY.register(name, new EntityType(name));
}
}
// Biomes
for (String name : fileRegistries.getDataFile().biomes) {
if (BiomeType.REGISTRY.get(name) == null) {
BiomeType.REGISTRY.register(name, new BiomeType(name));
}
}
// Tags
for (String name : fileRegistries.getDataFile().blocktags.keySet()) {
if (BlockCategory.REGISTRY.get(name) == null) {
BlockCategory.REGISTRY.register(name, new BlockCategory(name));
}
}
for (String name : fileRegistries.getDataFile().itemtags.keySet()) {
if (ItemCategory.REGISTRY.get(name) == null) {
ItemCategory.REGISTRY.register(name, new ItemCategory(name));
}
}
}
 ```

**Proposal:** I would suggest to extract a method containing for and every call of the method changes the arguments of it.


# 3.Switch statements

**Code location:** worldedit-cli/src/main/java/com.sk89q/schematic/CLIWorldEdit

The method main() has lots of conditions, that makes it confusing and difficult to find the condition specified.

```java
public static void main(String[] args) {
Options options = new Options();
options.addOption("f", "file", true, "The file to load in. Either a schematic, or a level.dat in a world folder.");
options.addOption("s", "script", true, "A file containing a list of commands to run. Newline separated.");
int exitCode = 0;

        CLIWorldEdit app = new CLIWorldEdit();
        app.onInitialized();

        InputStream inputStream = System.in;

        try {
            CommandLine cmd = new DefaultParser().parse(options, args);

            String fileArg = cmd.getOptionValue('f');
            File file;
            if (fileArg == null) {
                String[] formats = Arrays.copyOf(ClipboardFormats.getFileExtensionArray(), ClipboardFormats.getFileExtensionArray().length + 1);
                formats[formats.length - 1] = "dat";
                file = app.commandSender.openFileOpenDialog(formats);
            } else {
                file = new File(fileArg);
            }
            if (file == null) {
                throw new IllegalArgumentException("A file must be provided!");
            }
            LOGGER.info(() -> "Loading '" + file + "'...");
            if (file.getName().endsWith("level.dat")) {
                throw new IllegalArgumentException("level.dat file support is unfinished.");
            } else {
                ClipboardFormat format = ClipboardFormats.findByFile(file);
                if (format != null) {
                    int dataVersion;
                    if (format != BuiltInClipboardFormat.MCEDIT_SCHEMATIC) {
                        try (ClipboardReader dataVersionReader = format.getReader(
                            Files.newInputStream(file.toPath(), StandardOpenOption.READ)
                        )) {
                            dataVersion = dataVersionReader.getDataVersion()
                                .orElseThrow(() -> new IllegalArgumentException("Failed to obtain data version from schematic."));
                        }
                    } else {
                        dataVersion = Constants.DATA_VERSION_MC_1_13_2;
                    }
                    app.platform.setDataVersion(dataVersion);
                    app.onStarted();
                    ClipboardWorld world;
                    try (ClipboardReader clipboardReader = format.getReader(Files.newInputStream(file.toPath(), StandardOpenOption.READ))) {
                        world = new ClipboardWorld(
                                file,
                                clipboardReader.read(),
                                file.getName()
                        );
                    }
                    app.platform.addWorld(world);
                    WorldEdit.getInstance().getSessionManager().get(app.commandSender).setWorldOverride(world);
                } else {
                    throw new IllegalArgumentException("Unknown file provided!");
                }
            }
            LOGGER.info(() -> "Loaded '" + file + "'");

            String scriptFile = cmd.getOptionValue('s');
            if (scriptFile != null) {
                File scriptFileHandle = new File(scriptFile);
                if (!scriptFileHandle.exists()) {
                    throw new IllegalArgumentException("Could not find given script file.");
                }
                InputStream scriptStream = Files.newInputStream(scriptFileHandle.toPath(), StandardOpenOption.READ);
                InputStream newLineStream = new ByteArrayInputStream("\n".getBytes(StandardCharsets.UTF_8));
                // Cleaner to do this than make an Enumeration :(
                inputStream = new SequenceInputStream(new SequenceInputStream(scriptStream, newLineStream), inputStream);
            }

            app.run(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            exitCode = 1;
        } finally {
            app.onStopped();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.exit(exitCode);
    }
 ```
    
**Proposal:** extract the conditions to a new method or class and organize them.






