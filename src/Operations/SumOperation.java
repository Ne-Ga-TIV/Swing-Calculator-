package src.Operations;

import java.math.*;


/** An abstract class representing the addition operation
 * @author Anton Vaiciukevič
 * @version 1.0
 */
public class SumOperation extends Operation 
{
    /**@see SumOperation#SumOperation() */
    public SumOperation()
    {
        this.arg1 = BigDecimal.valueOf(0);
        arg1 = arg2 = result;
    }

    /** Сonstructor - defining arguments through a string
     * @see ModOperation#ModOperation(String, String)
    */
    public SumOperation(String arg1, String arg2)
    {
        super(arg1, arg2);
    }
    
    /** Сonstructor - defining arguments through a integer
     * @see SumOperation#SumOperation(String, String)
    */
    public SumOperation(int arg1, int arg2)
    {
        this.arg1 = BigDecimal.valueOf(arg1);
        this.arg2 = BigDecimal.valueOf(arg2);
    }


    /**
     * Overridden abstract calculation method for addition operation
     * @return calculate result
     */
    @Override
    public BigDecimal calculate()
    {
        result = arg1.add(arg2);

        return result;
    }

    /** Method for representing an operation as a string
     *  @return String
     */
    @Override
    public String toString()
    {
        return super.toString().replaceAll("   ", " + ");
    }

};
