package src.Operations;
import java.math.*;


/** An abstract class representing the divide operation
 * @author Anton Vaiciukevič
 * @version 1.0
 */
public class DivOperation extends Operation
{
    /**@see DivOperation#DivOperation() */
    public DivOperation()
    {
        super();
    }

    /** Сonstructor - defining arguments through a string
     * @see DivOperation#DivOperation(String, String)
    */
    public DivOperation(String arg1, String arg2)
    {
        super(arg1, arg2);
    }

     /** Сonstructor - defining arguments through a integer
     * @see DivOperation#DivOperation(String, String)
    */
    public DivOperation(int arg1, int arg2)
    {
        super(arg1, arg2);
    }
    
    /**
     * Overridden abstract calculation method for divide operation
     * @return calculate result
     */
    @Override
    public BigDecimal calculate()
    {
       
        result = arg1.divide(arg2, 3, RoundingMode.UP);

        return result;
    }
   
    /** Method for representing an operation as a string
     *  @return String
     */
    @Override
    public String toString()
    {
        return super.toString().replaceAll("  ", " / ");
    }

}           