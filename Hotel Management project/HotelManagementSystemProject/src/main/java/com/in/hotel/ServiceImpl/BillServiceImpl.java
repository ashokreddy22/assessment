package com.in.hotel.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Dao.BillDao;

import com.in.hotel.JWT.JwtFilter;
import com.in.hotel.Service.BillService;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.model.Bill;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {

		try {
			String fileName;
			if (validateRequestMap(requestMap)) {
				if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
					fileName = (String) requestMap.get("uuid");
				} else {
					fileName = HotelUtils.getUUID();
					requestMap.put("uuid", fileName);
					insertBill(requestMap);
				}
				String data = "Name:" + requestMap.get("name") + "\n" + "ContactNumber:"
						+ requestMap.get("contactNumber") + "\n" + "Email:" + requestMap.get("email") + "\n"
						+ "PaymentMethod:" + requestMap.get("paymentMethod");
				Document document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream(HotelConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));
				document.open();
				setRectanglePdf(document);

				Paragraph chunk = new Paragraph("Hotel Management System", getFont("Header"));
				chunk.setAlignment(Element.ALIGN_CENTER);
				document.add(chunk);
				Paragraph paragraph = new Paragraph(data + "\n\n", getFont(data));
				document.add(paragraph);

				PdfPTable table = new PdfPTable(5);// column
				table.setWidthPercentage(100);
				addTableHeader(table);

				JSONArray jsonArray = HotelUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
				for (int i = 0; i < jsonArray.length(); i++) {
					addRow(table, HotelUtils.getMapFromJson(jsonArray.getString(i)));
				}
				document.add(table);
				Paragraph footer = new Paragraph(
						"Total:" + requestMap.get("total") + "\n" + "Thank you for Visiting Visit Again!!",
						getFont("Data"));
				document.add(footer);
				document.close();
				return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
			}
			return HotelUtils.getResponseEntity("required Data Not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void addRow(PdfPTable table, Map<String, Object> data) {
		table.addCell((String) data.get("name"));
		table.addCell((String) data.get("category"));
		table.addCell((String) data.get("quantity"));
		table.addCell(Double.toString((Double) data.get("price")));
		table.addCell(Double.toString((Double) data.get("total")));
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Name", "Category", "Quantity", "price", "Total").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			header.setBackgroundColor(BaseColor.CYAN);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
		});
	}

	private Font getFont(String type) {

		switch (type) {
		case "Header":
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
			headerFont.setStyle(Font.BOLD);
			return headerFont;
		case "Data":
			Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
			dataFont.setStyle(Font.BOLD);
			return dataFont;
		default:
			return new Font();
		}

	}

	private void setRectanglePdf(Document document) throws DocumentException {
		Rectangle rect = new Rectangle(577, 825, 18, 15);

		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);

	}

	private void insertBill(Map<String, Object> requestMap) {
		try {
			Bill bill = new Bill();
			bill.setUuid((String) requestMap.get("uuid"));
			bill.setName((String) requestMap.get("name"));
			bill.setEmail((String) requestMap.get("email"));
			bill.setContactNumber((String) requestMap.get("contactNumber"));
			bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
			bill.setTotalAmount(Integer.parseInt((String) requestMap.get("total")));
			bill.setProductDetails((String) requestMap.get("productDetails"));
			bill.setCreateBy(jwtFilter.getCurrentUser());
			billDao.save(bill);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean validateRequestMap(Map<String, Object> requestMap) {

		return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
				&& requestMap.containsKey("email") && requestMap.containsKey("paymentMethod")
				&& requestMap.containsKey("productDetails") && requestMap.containsKey("total");

	}

	@Override
	public ResponseEntity<List<Bill>> getBills() {
		List<Bill> list = new ArrayList<>();
		if (jwtFilter.isAdmin()) {
			list = billDao.getAllBills();
		} else {
			list = billDao.getBillByUserName(jwtFilter.getCurrentUser());
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {

		try {
			byte[] byteArray = new byte[0];
			if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap))
				return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
			String filePath = HotelConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
			if (HotelUtils.isFileExist(filePath)) {
				byteArray = getByteArray(filePath);
				return new ResponseEntity<>(byteArray, HttpStatus.OK);
			} else {
				requestMap.put("isGenerate", false);
				generateReport(requestMap);
				byteArray = getByteArray(filePath);
				return new ResponseEntity<>(byteArray, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private byte[] getByteArray(String filePath) throws IOException {

		File initialFile = new File(filePath);
		InputStream targetStream = new FileInputStream(initialFile);
		byte[] byteArray = IOUtils.toByteArray(targetStream);
		targetStream.close();
		return byteArray;
	}

	@Override
	public ResponseEntity<String> deleteBill(Integer id) {
		
		try {
			Optional optional=billDao.findById(id);
			if(!optional.isEmpty()) {
				billDao.deleteById(id);
				return HotelUtils.getResponseEntity("Bill Deleted Successfully", HttpStatus.OK);
			}
			return HotelUtils.getResponseEntity("Bill id does not exist", HttpStatus.OK);
		} catch (Exception e) {
e.printStackTrace();		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
