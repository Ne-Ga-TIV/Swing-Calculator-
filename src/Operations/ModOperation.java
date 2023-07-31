package src.Operations;

import java.math.*;

/** An abstract class representing the modulus operation
 * @author Anton Vaiciukevič
 * @version 1.0
 */

public class ModOperation extends Operation
{
    /**@see ModOperation#ModOperation() */
    public ModOperation()
    {
        super();
    }


    /** Сonstructor - defining arguments through a string
     * @see ModOperation#ModOperation(String, String)
    */
    public ModOperation(String arg1, String arg2)
    {
        super(arg1, arg2);
    }

    /** Сonstructor - defining arguments through a integer
     * @see ModOperation#ModOperation(String, String)
    */
    public ModOperation(int arg1, int arg2)
    {
        super(arg1, arg2);
    }
    
    /**
     * Overridden abstract calculation method for module operation
     * @return calculate result
     */
    @Override
    public BigDecimal calculate()
    {
        result = arg1.remainder(arg2);

        return result;
    }

    /** Method for representing an operation as a string
     *  @return String
     */
    @Override
    public String toString()
    {
        return super.toString().replaceAll("   ", " % ");
    }

}           