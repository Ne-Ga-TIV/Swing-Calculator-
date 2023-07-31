package src.Operations;

import java.math.BigDecimal;

/** An abstract class representing the subtraction operation
 * @author Anton Vaiciukevič
 * @version 1.0
 */
public class SubOperation extends Operation
{
    /**@see SubOperation#SubOperation() */
    public SubOperation()
    {
        super();
    }

    /** Сonstructor - defining arguments through a string
     * @see ModOperation#ModOperation(String, String)
    */
    public SubOperation(String arg1, String arg2)
    {
        super(arg1, arg2);
    }

    /** Сonstructor - defining arguments through a integer
     * @see ModOperation#ModOperation(String, String)
    */
    public SubOperation(int arg1, int arg2)
    {
        super(arg1, arg2);
    }

     /**
     * Overridden abstract calculation method for subtraction operation
     * @return calculate result
     */
    @Override
    public BigDecimal calculate()
    {
        result = arg1.subtract(arg2);

        return result;
    }


    /** Method for representing an operation as a string
     *  @return String
     */
    @Override
    public String toString()
    {
        return super.toString().replaceAll("   ", " - ");
    }
    
}
