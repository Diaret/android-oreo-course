package jdd;

public class NumberShapes {

	public static void main(String[] args) {
		class Number{
			private int number;

			public int getNumber() {
				return number;
			}

			public void setNumber(int number) {
				this.number = number;
			}

			public boolean isTriangular() {
				double x;
				x = (-1 + Math.sqrt(1+8*getNumber()))/2;
				if (x == Math.round(x) & x > 0)  {
					return true;
				} else {
					return false;
				}

			}
			
			public boolean isSquare() {
				double x;
				x = Math.sqrt(getNumber());
				if (x == Math.round(x))  {
					return true;
				} else {
					return false;
				}

			}
		}

		Number num = new Number();
		num.setNumber(1);
		System.out.println(num.getNumber() + " is triangular: " + num.isTriangular());
		System.out.println(num.getNumber() + " is square: " + num.isSquare());
		
		num.setNumber(9);
		System.out.println(num.getNumber() + " is triangular: " + num.isTriangular());
		System.out.println(num.getNumber() + " is square: " + num.isSquare());
		
	}



}
