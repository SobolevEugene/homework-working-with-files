package com.sample.drivers.screen.driver;

import io.jmix.ui.component.Image;
import io.jmix.ui.component.ResourceView;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
	@Autowired
	private Image<byte[]> photoField;

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


}