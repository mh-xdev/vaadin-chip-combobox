package software.xdev.vaadin.chips;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Chip-Combobox Demo")
public class SimpleDemo extends HorizontalLayout
{
	private static final Random RANDOM = new Random();
	
	private final VerticalLayout vlLeft = new VerticalLayout();
	private final VerticalLayout vlRight = new VerticalLayout();
	
	private final ChipComboBox<String> stringBox = new ChipComboBox<String>()
		.withLabel("Programming languages")
		.withPlaceholder("Select programming language")
		.withFullComboBoxWidth();
	
	private final Button btnRestoreStringDefaults = new Button("Restore default selected values");
	
	private final TextArea taValueChangeString =
		new TextArea("ValueChangeEvent", "Change something in the chip combobox to see the result");
	
	private final ChipComboBox<Integer> intBox = new ChipComboBox<Integer>()
		.withPlaceholder("Select Integer chips");
	
	private final Button btnSetAvailableInts1to10 = new Button("Set available ints 1-10");
	private final Button btnSetAvailableInts5to15 = new Button("Set available ints 5-15");
	private final Button btnSetAvailableInts11to20 = new Button("Set available ints 11-20");
	private final Button btnSetRandomAvailableInts = new Button("Set available ints random");
	private final Button btnShowSelectedInt = new Button("What Integers are selected?");
	
	private final TextArea taValueChangeInt =
		new TextArea("ValueChangeEvent", "Change something in the chip combobox to see the result");
	
	public SimpleDemo()
	{
		this.initUI();
		
		this.stringBox.addValueChangeListener(ev ->
			// @formatter:off
			this.taValueChangeString.setValue(
					"Value: [" + ev.getValue().stream().collect(Collectors.joining(", ")) + "] \r\n" +
					"OldValue: [" + ev.getOldValue().stream().collect(Collectors.joining(", ")) + "] \r\n" +
					"IsFromClient: " + ev.isFromClient()
			)
			// @formatter:on
		);
		
		this.btnSetAvailableInts1to10.addClickListener(ev -> this.setAvailableInts(1, 10));
		this.btnSetAvailableInts5to15.addClickListener(ev -> this.setAvailableInts(5, 15));
		this.btnSetAvailableInts11to20.addClickListener(ev -> this.setAvailableInts(11, 20));
		this.btnSetRandomAvailableInts.addClickListener(ev -> this.setAvailableIntsRandom());
		
		this.btnShowSelectedInt.addClickListener(ev ->
			Notification.show("Selected: " + this.intBox.getValue().stream().map(i -> i.toString()).collect(Collectors.joining(", ")))
		);
		
		this.intBox.addValueChangeListener(ev ->
			// @formatter:off
			this.taValueChangeInt.setValue(
					"Value: [" + ev.getValue().stream().map(i -> i.toString()).collect(Collectors.joining(", ")) + "] \r\n" +
					"OldValue: [" + ev.getOldValue().stream().map(i -> i.toString()).collect(Collectors.joining(", ")) + "] \r\n" +
					"IsFromClient: " + ev.isFromClient()
			)
			// @formatter:on
		);
	}
	
	private void initUI()
	{
		final SplitLayout slLimit = new SplitLayout(new VerticalLayout(this.stringBox), new Div());
		slLimit.setOrientation(Orientation.HORIZONTAL);
		slLimit.setSplitterPosition(40);
		slLimit.setWidthFull();
		
		this.btnRestoreStringDefaults.addClickListener(ev -> this.restoreStringDefaults());
		
		this.taValueChangeString.setReadOnly(true);
		
		this.taValueChangeInt.setReadOnly(true);
		
		this.vlLeft.add(
			slLimit,
			this.btnRestoreStringDefaults,
			this.taValueChangeString);
		
		this.vlRight.add(
			this.intBox,
			this.btnSetAvailableInts1to10,
			this.btnSetAvailableInts5to15,
			this.btnSetAvailableInts11to20,
			this.btnSetRandomAvailableInts,
			this.btnShowSelectedInt,
			this.taValueChangeInt);
		
		this.add(this.vlLeft, this.vlRight);
		this.setWidthFull();
	}
	
	private void restoreStringDefaults()
	{
		this.stringBox.setValue(Arrays.asList("Java", "Kotlin", "C#", "Python"));
	}
	
	private void setAvailableIntsRandom()
	{
		final int startValue = RANDOM.nextInt(100) + 1;
		final int count =  RANDOM.nextInt(5) + 5;
		
		this.setAvailableInts(startValue, startValue + count);
	}
	
	private void setAvailableInts(final int startInclusive, final int endInclusive)
	{
		this.intBox.withAllAvailableItems(IntStream.rangeClosed(startInclusive, endInclusive).boxed().collect(Collectors.toList()));
	}
	
	@Override
	protected void onAttach(final AttachEvent attachEvent)
	{
		this.stringBox
			.withAllAvailableItems(Arrays.asList("Java", "TypeScript", "Shell", "JavaScript", "Kotlin", "C#", "Python"));
		
		this.setAvailableIntsRandom();
		
		this.restoreStringDefaults();
	}
	
}
