package test.bin;

import bin.CreatePDF;

public class CreatePDFTest {

	public static void main(String[] args) {
		String pdf_id = "qEPy8nOW";
		boolean flg = CreatePDF.createPDF(pdf_id);
		System.out.println(flg);
	}
}