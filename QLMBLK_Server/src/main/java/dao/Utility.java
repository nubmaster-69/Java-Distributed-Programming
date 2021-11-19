package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import facade.IUtilityFacade;

public class Utility extends UnicastRemoteObject implements IUtilityFacade {

	private static final long serialVersionUID = 1L;

	public Utility() throws RemoteException {
	}

	/**
	 * <p>
	 * <i>Phương thức có chức năng xuất file thống kê ra file Excel</i>
	 * </p>
	 * 
	 * @author Hiếu
	 * @param res      ResultSet chứa dữ liệu sẽ được xuất ra file excel
	 * @param type     loại thống kê, vd: ThongKeSanPhamBanChay,...
	 * @param filePath Đường dẫn sẽ lưu file mà người dùng đã chọn/chỉ định
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 */
	@Override
	public void exportFileToExcel(ResultSet res, String type, String filePath) throws RemoteException {
		@SuppressWarnings("resource")
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet(type);

		try {
			ResultSetMetaData meta = res.getMetaData();
			Row headerRow = sheet.createRow(0);

			for (int i = 0; i < meta.getColumnCount(); i++)
				headerRow.createCell(i).setCellValue(meta.getColumnName(i + 1));

			int contentRowCount = 1;
			while (res.next()) {
				Row currentRow = sheet.createRow(contentRowCount++);
				for (int i = 0; i < meta.getColumnCount(); i++)
					currentRow.createCell(i).setCellValue(res.getString(i + 1));
			}

			try (FileOutputStream fos = new FileOutputStream(
					String.format("%s/%s.xlsx", filePath, formatFileName(type)))) {
				workBook.write(fos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String formatFileName(String type) throws RemoteException {
		String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
		return String.format("%s_%s", type, date);
	}
}