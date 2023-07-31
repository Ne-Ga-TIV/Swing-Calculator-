package src.Operations;
import java.io.Serializable;
import java.math.BigDecimal;

/** An abstract class representing a calculation operation
 * @author Anton Vaiciukevič
 * @version 1.1
 */

public abstract class Operation 
                    implements  Serializable
{
    /** Argument fields to calculate */
    protected BigDecimal arg1, arg2 , result;
    
    /** @see Operation#Operation() */
    public Operation(){}

    /** Сonstructor - defining arguments through a string
     * @see Operation#Operation(String, String)
    */
    public Operation(String arg1, String arg2)
    {
        this.arg1 = new BigDecimal(arg1);
        this.arg2 = new BigDecimal(arg2);
    }
    

    /** Сonstructor - defining arguments through a integer
     * @see Operation#Operation(String, String)
    */
    public Operation(int arg1, int arg2)
    {
        this.arg1 = BigDecimal.valueOf(arg1);
        this.arg2 = BigDecimal.valueOf(arg2);
    }

    /**
     * An abstract method for computing future operations
     * @return calculate result
     */
    public abstract  BigDecimal calculate();


    /** Field clearing method */
    public void clear()
    {
        result = BigDecimal.ZERO;
        arg1 = arg2 = result;
    }

    /** Method for adding a minus to the result */
    public void negate()
    {
        result = result.negate();
    }
    
    /** Method for representing an operation as a string
     *  @return String
     */
    public String toString()
    {
        return arg1 + "   " + arg2 + " = " + result;
    }

    /**
     * Method that returns the result of a calculation
     * @return result
     */
    public final BigDecimal getResult(){ return result; }

    /** Method for setting new first argument
     * @param arg1 new argument
     */
    public final void setArg1(BigDecimal arg1){ this.arg1 = arg1; }

    /** Method for setting new second argument
     * @param arg2 new argument
     */
    public final void setArg2(BigDecimal arg2){ this.arg2 = arg2; }

    /** The method returns the first argument
     * @return first argument
     */
    public final BigDecimal getArg1(){ return this.arg1; }
    
    /** The method returns the second argument
     * @return first argument
     */
    public final BigDecimal getArg2(){return this.arg2; }
}
