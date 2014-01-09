package gameobjects.items;

import constants.Constants;

/**
 * The Elixir of Life helps healing, by an amount defined in the constants.
 * Overrides onHeal.
 */
final class ElixirOfLife extends Item {

    /**
     * Items are created using reflection, the constructor should not be called
     * for other than testing purposes.
     */
    public ElixirOfLife() {
        super(ElixirOfLife.class);
    }

    @Override
    public int onHeal(int amount) {
        return (int) (amount * Constants.ELIXIR_OF_LIFE_HEALTH_MODFIER);
    }
}
