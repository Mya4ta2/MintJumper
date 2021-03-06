package mint.runner.maps;

import com.badlogic.gdx.files.FileHandle;
import mint.runner.content.Blocks;
import mint.runner.content.Overlays;
import mint.runner.content.Walls;
import mint.runner.ctype.MappableContent;
import mint.runner.type.Block;
import mint.runner.type.Overlay;
import mint.runner.type.Wall;
import mint.runner.type.World;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldReader {
    public static World readFile(FileHandle file) throws Exception {
        if (file.exists()) {
            World world;

            String mapFileStr = file.readString();
            String[] strings = mapFileStr.split(";");
            String str1, str2;
            int[][] blockPositions;

            Pattern blockPositionPattern = Pattern.compile("\\((\\[\\d+,\\d+],)+\\)", Pattern.CASE_INSENSITIVE);
            Pattern contentTypePattern = Pattern.compile("(\\D+)", Pattern.CASE_INSENSITIVE);
            Pattern worldSizePattern = Pattern.compile("width=(\\d+) height=(\\d+)", Pattern.CASE_INSENSITIVE);

            //find world size on first line
            Matcher worldSizeMatcher = worldSizePattern.matcher(strings[0]);

            if (worldSizeMatcher.find()) {
                world = new World(Integer.parseInt(worldSizeMatcher.group(1)), Integer.parseInt(worldSizeMatcher.group(2)), file.nameWithoutExtension());
            } else {
                throw new Exception("MapReader can't find world size on first line");
            }
            //

            for (String str : strings) {
                Matcher blockPositionMatcher = blockPositionPattern.matcher(str);
                Matcher contentTypeMatcher = contentTypePattern.matcher(str);

                while (blockPositionMatcher.find()) {
                    blockPositions = parseArray(
                            blockPositionMatcher.group(0)
                                    .replace("("," ")
                                    .replace(")"," ")
                                    .substring(0, blockPositionMatcher.group(0).length()-2)
                                    .trim()
                    );

                    if (contentTypeMatcher.find()) {
                        String[] contentTypes = contentTypeMatcher.group(0).replace("([", " ").trim().split("\\.");
                        String contentType = contentTypes[0];
                        String contentName = contentTypes[1];
                        MappableContent contentTypeObj = null;

                        if (contentType.equals("Blocks")) {
                            for (Block block : Blocks.blocks) {
                                if (block.name.equals(contentName)) {
                                    contentTypeObj = block;
                                }
                            }

                            if (contentTypeObj == null) throw new Exception("error find block" + contentName);

                            for (int i = 0; i < blockPositions.length; i++) {
                                world.tiles.get(
                                        blockPositions[i][0],
                                        blockPositions[i][1]
                                ).block = ((Block) contentTypeObj);
                            }
                        } else if (contentType.equals("Walls")) {
                            for (Wall wall : Walls.walls) {
                                if (wall.name.equals(contentName)) {
                                    contentTypeObj = wall;
                                }
                            }

                            if (contentTypeObj == null) throw new Exception("error find wall" + contentName);

                            for (int i = 0; i < blockPositions.length; i++) {
                                world.tiles.get(
                                        blockPositions[i][0],
                                        blockPositions[i][1]
                                ).wall = ((Wall) contentTypeObj);
                            }
                        } else if (contentType.equals("Overlays")) {
                            for (Overlay overlay : Overlays.overlays) {
                                if (overlay.name.equals(contentName)) {
                                    contentTypeObj = overlay;
                                }
                            }

                            if (contentTypeObj == null) throw new Exception("error find overlay " + contentName);

                            for (int i = 0; i < blockPositions.length; i++) {
                                world.tiles.get(
                                        blockPositions[i][0],
                                        blockPositions[i][1]
                                ).overlay = ((Overlay) contentTypeObj);
                            }
                        }
                    }
                }
            }

            world.setTiles();
            return world;
        } else {
            throw new FileNotFoundException("MapReader can't find the map file");
        }
    }

    public static int[][] parseArray(String str) {
        str = str.replace(" ","");
        str = str.replace("[", "").replace("]", "");
        str = str.replace(",", " ");
        String[] num = str.split(" ");
        int[] numbers = new int[num.length];
        int[][] out = new int[num.length/2][2];

        for (int i = 0; i < num.length; ++i)
        {
            numbers[i] = Integer.parseInt(num[i]);
        }

        int j = 0;
        int k = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (j > 1) {
                j = 0;
                k += 2;
            }
            j++;
            out[i/2][0] = numbers[k];
            out[i/2][1] = numbers[k + 1];
        }

        return out;
    }
}
