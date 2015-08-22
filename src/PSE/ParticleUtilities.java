package PSE;

import java.util.Random;

public class ParticleUtilities {
	private static Random r = new Random();
	
	// ***************UTILIY METHODS*****************//
	public final static float InvSqrt(float x) {
		return Float
				.intBitsToFloat(0x5f3759d5 - (Float.floatToIntBits(x) >> 1));
	}

	public final static int additiveColor(int c1, int c2) {
		int red = (c1 & 0x00ff0000) + (c2 & 0x00ff0000);
		int grn = (c1 & 0x0000ff00) + (c2 & 0x0000ff00);
		int blu = (c1 & 0x000000ff) + (c2 & 0x000000ff);
		return 0xff000000 + (red > 0x00ff0000 ? 0x00ff0000 : red)
				+ (grn > 0x0000ff00 ? 0x0000ff00 : grn)
				+ (blu > 0x000000ff ? 0x000000ff : blu);
	}

	public static int makeIntColor(int A, int R, int G, int B) {
		return (A > 255 ? 0xff000000 : A << 24)
				+ (R > 255 ? 0xff0000 : R << 16) + (G > 255 ? 0xff00 : G << 8)
				+ (B > 255 ? 0xff : B);
	}
	
	public static Random getRandom(){
		return r;
	}
	// ***************END UTILIY METHODS*****************//
}
