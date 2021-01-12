package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NeighborAirState {
    public NeighborAir neighborAir;
    public TextureRegion currentBlockTexture;

    public NeighborAirState(NeighborAir neighborAir) {
        this.neighborAir = neighborAir;
        currentBlockTexture = neighborAir.middle;
    }
}
