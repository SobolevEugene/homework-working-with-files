package com.sample.drivers.screen.driver;

import com.sample.drivers.entity.Document;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Image;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.component.ResourceView;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
	@Autowired
	private Image<byte[]> photoField;
	@Autowired
	private UiComponents uiComponents;
	@Autowired
	private Downloader downloader;

	@Subscribe
	public void onBeforeShow(final BeforeShowEvent event) {
		checkPhotoVisibility();
	}


	@Subscribe("photoField")
	public void onPhotoFieldSourceChange(final ResourceView.SourceChangeEvent event) {
		checkPhotoVisibility();
	}

	private void checkPhotoVisibility() {
		photoField.setVisible(getEditedEntity().getPhoto() != null);
	}

	@Install(to = "documentsTable.file", subject = "columnGenerator")
	private Component documentsTableFileColumnGenerator(final Document document) {
		if (document.getFile() == null) {
			return null;
		}
		var saveButton = uiComponents.create(LinkButton.class);
		saveButton.setCaption(document.getFile().getFileName());
		saveButton.addClickListener(clickEvent ->
				downloader.download(document.getFile()));
		return saveButton;
	}


}