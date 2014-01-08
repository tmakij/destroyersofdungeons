package gameobjects.items;

import constants.Constants;

final class ElixirOfLife extends Item {

    public ElixirOfLife() {
        super(ElixirOfLife.class);
    }

    @Override
    public int onHeal(int amount) {
        return (int) (amount * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER);
    }
}
