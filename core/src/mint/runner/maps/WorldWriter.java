package mint.runner.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import mint.runner.Vars;
import mint.runner.content.Blocks;
import mint.runner.type.World;

public class WorldWriter {
    public static final String fileExtension = "rsav";
    public static final String worldsDir = Vars.worldDir;

    public static String writeNewMap(String fileName, World world) {
        FileHandle file = createFile(fileName);

        //write world size on first line
        file.writeString("width=" + world.width + " height=" + world.height + ";\n",true);

        //write content
        for (int i = 0; i < world.tiles.array.length; i++) {
//            if (world.tiles.array[i]. != Floors.air) {
//                file.writeString("Floors." + world.getTiles().getArray()[i].getFloor().getName() + " (",true);
//                file.writeString(
//                        "[" + world.getTiles().getArray()[i].getX()
//                                + "," +
//                                world.getTiles().getArray()[i].getY() + "],",
//                        true
//                );
//                file.writeString(");\n",true);
//            }

            if (world.tiles.array[i].block != Blocks.air) {
                file.writeString("Blocks." + world.tiles.array[i].block.name + " (",true);
                file.writeString(
                        "[" + world.tiles.array[i].x
                                + "," +
                                world.tiles.array[i].y + "],",
                        true
                );
                file.writeString(");\n",true);
            }
        }
        //

        return file.readString();
    }

    public static FileHandle createFile(String fileName) {
        FileHandle file = Gdx.files.local(worldsDir + "\\" + fileName + "." + fileExtension);
        file.writeString("",false);
        return file;
    }

    public static World cutWorldToSize(World world, int width, int height) {
        World newWorld = new World(width, height, world.name);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                newWorld.tiles.set(x, y, world.tiles.get(x,y));
            }
        }

        return newWorld;
    }
}
