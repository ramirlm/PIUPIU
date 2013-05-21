package info.noip.piupiu.infra;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Convert(UploadedFile.class)
public class UploadedFileConverter implements Converter<UploadedFile> {

	private final HttpServletRequest request;

	public UploadedFileConverter(HttpServletRequest request) {
		this.request = request;
	}

	public UploadedFile convert(String value,
			Class<? extends UploadedFile> type, ResourceBundle bundle) {
		Object upload = request.getAttribute(value);
		if (upload == null) {
			throw new ConversionError("Invalid upload");
		}
		return type.cast(upload);
	}

}
