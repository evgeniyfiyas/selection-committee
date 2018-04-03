package by.grsu.fiyas.web.page.faculty.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.web.app.AuthorizedSession;
import by.grsu.fiyas.web.page.faculty.FacultiesPage;
import by.grsu.fiyas.web.page.faculty.FacultyEditPage;

public class FacultyListPanel extends Panel {

	private FacultyService facultyService;
	
	public FacultyListPanel(String id, String rootFolderPath) {
		super(id);
		facultyService = new FacultyServiceImpl(rootFolderPath);
		FacultyDataProvider facultyDataProvider = new FacultyDataProvider();
		
		DataView<Faculty> facultyDataView = new DataView<Faculty>("rows", facultyDataProvider) {
			@Override
			protected void populateItem(Item<Faculty> item) {
				Faculty faculty = item.getModelObject();
				item.add(new InvisibleLabel("id", faculty.getId()));
				item.add(new Label("name", faculty.getName()));
				item.add(new Label("selectionPlan", faculty.getSelectionPlan()));
				item.add(new MultiLineLabel("subjects", facultyDataProvider.SubjectsRenderer(faculty)));
				CheckBox checkbox = new CheckBox("active", new PropertyModel<>(faculty, "isEnabled"));
				checkbox.setEnabled(false);
				item.add(checkbox);
				
				item.add(new InvisibleLink<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new FacultyEditPage(faculty));
					}
				});

				item.add(new InvisibleLink<Void>("delete-link") {
					@Override
					public void onClick() {
						FacultiesPage page = new FacultiesPage();
						try {
							facultyService.delete(faculty.getId());
							page.success("Faculty removed.");
						} catch (PersistenceException e) {
							page.error("Error removing faculty.");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
		};
		add(facultyDataView);
	}	
	
	
	private class FacultyDataProvider extends SortableDataProvider<Faculty, Serializable> {

		@Override
		public Iterator<Faculty> iterator(long first, long count) {
			return facultyService.getAll().iterator();
		}

		@Override
		public long size() {
			return facultyService.getAll().size();
		}

		@Override
		public IModel<Faculty> model(Faculty object) {
			return new Model(object);
		}
		
		private String SubjectsRenderer(Faculty object) {
			String result = "";
			for(AcademicSubject subject : object.getSubjects()) {
	            result += subject.getName() + "\n";
	        }
			return result;
		}
	}

	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class InvisibleLink<Void> extends Link<Void> {

		public InvisibleLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
		}
	}

	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class InvisibleLabel extends Label {

		public InvisibleLabel(String id, Serializable label) {
			super(id, label);
		}
	}
}