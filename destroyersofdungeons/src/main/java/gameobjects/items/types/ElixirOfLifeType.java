package gameobjects.items.types;

/**
 * ElixirOfLife ItemType.
 */
final class ElixirOfLifeType extends ItemType {

    /**
     * ItemTypes are loaded using reflection, the constructor should never be
     * called.
     *
     * @param id The unique id.
     */
    public ElixirOfLifeType(int id) {
        super(id, "ELIXIR_OF_LIFE");
    }
}
