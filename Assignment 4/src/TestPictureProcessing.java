public class TestPictureProcessing {

	public static void main(String[] args) {
		String fileName = "t3.pgm";
		// check copy
		PictureProcessing p = new PictureProcessing(fileName);
		// check rotate
		p.rotate90Pict("t3_90.pgm");
		// check average smoothing
		p.ave3_3("t3_smooth.pgm");
		// check mode smoothing
		p.mode3_3 ("t3_mode_smooth.pgm");
		// check subpicture
		p.sub_picture("t3_sub_pict.pgm",300,300,800,600);
	}
}
