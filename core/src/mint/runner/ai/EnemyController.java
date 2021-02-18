package mint.runner.ai;

import mint.runner.type.Enemy;
import mint.runner.type.Player;

public class EnemyController {
    public Enemy enemy;
    public Player target;

    public EnemyController(Player target) {
        this.target = target;
    }

    public void update(float delta) {
        if (target.position.dst(enemy.position) < 5) {
            float xDifference = (target.position.x - enemy.position.x) / 20;
            float yDifference = (target.position.y - enemy.position.y) / 20;

            enemy.position.add(xDifference, yDifference);
        };
    }
}
