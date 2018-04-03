package by.grsu.fiyas.web.page.student;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.web.page.AbstractPage;

public class StudentRegisterPageDetails extends AbstractPage {

	public StudentRegisterPageDetails(Faculty faculty) {
		super();

		Form<?> form = new Form<Void>("form");
        add(form);
		
		Label subj1lbl = new Label("subj1lbl", faculty.getSubjects().get(0).getName());
		TextField<Integer> subj1 = new TextField<Integer>("subj1");
		subj1.setRequired(true);
		form.add(subj1);
		subj1lbl.setOutputMarkupId(true);
		form.add(subj1lbl);

		Label subj2lbl = new Label("subj2lbl", faculty.getSubjects().get(1).getName());
		TextField<Integer> subj2 = new TextField<Integer>("subj2");
		subj2.setRequired(true);
		form.add(subj2);
		form.add(subj2lbl);

		Label subj3lbl = new Label("subj3lbl", faculty.getSubjects().get(2).getName());
		TextField<Integer> subj3 = new TextField<Integer>("subj3");
		subj3.setRequired(true);
		form.add(subj3);
		form.add(subj3lbl);        

		TextField<Integer> certificate = new TextField<Integer>("certificate");
		certificate.setRequired(true);
		form.add(certificate);

	}
}
