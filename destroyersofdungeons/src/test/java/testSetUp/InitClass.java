package testSetUp;

import localisation.Dictionary;
import org.junit.BeforeClass;

public abstract class InitClass {

    @BeforeClass
    public static void setUpStatic() {
        Dictionary.loadText("english");
    }

    protected InitClass() {
    }
}
