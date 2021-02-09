package mint.runner.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RoundingState {
    public Rounding rounding;
    public TextureRegion currentBlockTexture;

    public RoundingState(Rounding rounding) {
        this.rounding = rounding;
        currentBlockTexture = rounding.middle;
    }
}
