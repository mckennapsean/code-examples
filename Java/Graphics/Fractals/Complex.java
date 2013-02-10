// Copyright 2013 Sean McKenna
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

// complex numbers object
// form: c = a + b * i (i=sqrt(-1))
// works with Fractals.java

public class Complex {
  private double cReal = 0;
  private double cImaginary = 0;
  
  public Complex(double a,double b){
    cReal = a;
    cImaginary = b;
  }

  //c + d in complex numbers (add both real and imaginary parts separately)
  public Complex add(Complex d){
    double re = cReal + d.getReal();
    double im = cImaginary + d.getImaginary();
    return new Complex(re,im);
  }

  //c * d in complex numbers
  //for real portion: multiply real parts and subtract multiple imaginary ones
  //for imaginary portion: first real * second im + first im * second real
  public Complex multiply(Complex d){
    double re = (cReal * d.getReal()) - (cImaginary * d.getImaginary());
    double im = (cReal * d.getImaginary()) + (cImaginary * d.getReal());
    return new Complex(re,im);
  }

  //c * c in complex numbers (utilizes the multiply method)
  public Complex squared(){
    return this.multiply(this);
  }

  //returns the real portion of a complex number
  public double getReal(){
    return cReal;
  }

  //returns the imaginary portion of a complex number
  public double getImaginary(){
    return cImaginary;
  }
}
