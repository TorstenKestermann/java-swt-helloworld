import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HelloResponsive {

	public static void main(String[] args) {

		// Create display and shell that constitutes the opened dialog
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello");
		shell.setSize(400, 300);

		// Create resources later used and set background color of shell
		FontData systemFontData = display.getSystemFont().getFontData()[0];
		Font bold = new Font(display, systemFontData.getName(), systemFontData.getHeight(), SWT.BOLD);
		Color gray = new Color(display, 242, 242, 242);
		shell.setBackground(gray);

		// Attach a grid layout (defining two columns) to shell
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		shell.setLayout(gridLayout);

		// Then add widgets to shell and configure them properly
		final Label label = new Label(shell, SWT.NONE);
		GridData gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
		gridData.horizontalSpan = 2;
		gridData.verticalIndent = 50;
		label.setLayoutData(gridData);
		label.setBackground(shell.getBackground());
		label.setFont(bold);
		label.setText("What's your Name?");

		// Create a composite hosting text and button to be able to layout it as single unit
		final Composite composite = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
		rowLayout.center = true;
		composite.setLayout(rowLayout);
		gridData = new GridData(SWT.CENTER, SWT.TOP, true, false);
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);
		composite.setBackground(shell.getBackground());
		final Text text = new Text(composite, SWT.BORDER);
		RowData rowData = new RowData();
		rowData.width = 200;
		text.setLayoutData(rowData);
		text.setTextLimit(30);
		final Button button = new Button(composite, SWT.PUSH);
		button.setBackground(composite.getBackground());
		button.setText("Submit");
		button.setEnabled(false);
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				label.setText("Hello " + text.getText() + "!");
				label.getParent().layout();
			}
		});
		shell.setDefaultButton(button);

		text.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				button.setEnabled(!text.getText().isEmpty());
			}
		});

		// Open shell and start infinite application loop transferring input events
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// After loop has finished, dispose of created resources
		bold.dispose();
		gray.dispose();
		display.dispose();
	}
}
