package software.xdev.vaadin.chips;

/*-
 * #%L
 * ChipComboBox for Vaadin
 * %%
 * Copyright (C) 2021 XDEV Software
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.shared.Registration;


/**
 * Represents a Chip shown underneath the {@link ChipComboBox}
 * 
 * @author DL
 * @author AB
 */
public class ChipComponent extends Composite<HorizontalLayout> implements
	HasStyle,
	HasSize
{
	protected final Button btnDelete = new Button(VaadinIcon.CLOSE_CIRCLE.create());
	protected final Label label = new Label();
	
	public ChipComponent()
	{
		this.initUI();
	}
	
	protected void initUI()
	{
		this.label.getStyle().set("font-size", "var(--lumo-font-size-s)");
		
		this.btnDelete.setDisableOnClick(true);
		this.btnDelete.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY_INLINE);
		this.btnDelete.getStyle().set("font-size", "var(--lumo-font-size-m)");
		
		this.label.setSizeUndefined();
		this.btnDelete.setSizeUndefined();
		
		this.getContent().setSpacing(false);
		
		final Style style = this.getContent().getStyle();
		style.set("background-color", "var(--lumo-contrast-10pct)");
		style.set("border-radius", "var(--lumo-font-size-s)");
		style.set("margin", "var(--lumo-space-xs)");
		style.set("padding-left", " var(--lumo-space-s)");
		
		this.getContent().add(this.label, this.btnDelete);
	}
	
	public void withLabelText(final String text)
	{
		this.label.setText(text);
	}
	
	public Registration addBtnDeleteClickListener(final ComponentEventListener<ClickEvent<Button>> listener)
	{
		return this.btnDelete.addClickListener(listener);
	}

	public void setReadonly(final boolean readOnly)
	{
		this.btnDelete.setEnabled(!readOnly);
	}
}
