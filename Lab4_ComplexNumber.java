import java.lang.Math;

public class ComplexNumber {
    
    private double real, fake;
    
    public ComplexNumber() {
        this(0, 0);
    }

    public ComplexNumber(double realPart, double fakePart) {
        this.real = realPart;
        this.fake = fakePart;
    }

    public ComplexNumber summ(ComplexNumber second){
        return new ComplexNumber(this.real + second.real, this.fake + second.fake);
    }

    public ComplexNumber summ(double realPart, double fakePart){
        return new ComplexNumber(this.real + realPart, this.fake + fakePart);
    }

    public ComplexNumber mult(double constant) {
        return new ComplexNumber(this.real * constant, this.fake * constant);
    }
    public ComplexNumber mult(ComplexNumber second) {
        return new ComplexNumber(this.real * second.real - this.fake * second.fake, this.real * second.fake + this.fake * second.real);
    }

    public double abs() {
        return Math.sqrt(this.real * this.real + this.fake * this.fake);
    }

    public double absPow() {
        return this.real * this.real + this.fake * this.fake;
    }
}
