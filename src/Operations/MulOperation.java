package src.Operations;

import java.math.*;



/** An abstract class representing the multiplication operation
 * @author Anton Vaiciukevič
 * @version 1.0
 */
public class MulOperation extends Operation
{
    /**@see MulOperation#MulOperation() */
    public MulOperation()
    {
        super();
    }

     /** Сonstructor - defining arguments through a string
     * @see MulOperation#MulOperation(String, String)
    */
    public MulOperation(String arg1, String arg2)
    {
        super(arg1, arg2);
    }

    /** Сonstructor - defining arguments through a integer
     * @see MulOperation#MulOperation(String, String)
    */
    public MulOperation(int arg1, int arg2)
    {
        super(arg1, arg2);
    }
    
    /**
     * Overridden abstract calculation method for multiplication operation
     * @return calculate result
     */
    @Override
    public BigDecimal calculate()
    {
        result = arg1.multiply(arg2);

        return result;
    }

    
    /** Method for representing an operation as a string
     *  @return String
     */
    @Override
    public String toString()
    {
        return super.toString().replaceAll("   ", " * ");
    }

}           