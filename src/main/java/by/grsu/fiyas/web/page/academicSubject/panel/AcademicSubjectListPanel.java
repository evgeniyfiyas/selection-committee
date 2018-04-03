package by.grsu.fiyas.web.page.academicSubject.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.service.AcademicSubjectService;
import by.grsu.fiyas.service.impl.AcademicSubjectServiceImpl;
import by.grsu.fiyas.web.app.AuthorizedSession;
import by.grsu.fiyas.web.page.academicSubject.AcademicSubjectEditPage;
import by.grsu.fiyas.web.page.academicSubject.AcademicSubjectsPage;

public class AcademicSubjectListPanel extends Panel {

	private AcademicSubjectService academicSubjectService;
	
	public AcademicSubjectListPanel(String id, String rootFolderPath) {
		super(id);
		academicSubjectService = new AcademicSubjectServiceImpl(rootFolderPath);
		AcademicSubjectDataProvider academicSubjectDataProvider = new AcademicSubjectDataProvider();
		
		DataView<AcademicSubject> academicSubjectDataView = new DataView<AcademicSubject>("rows", academicSubjectDataProvider) {
			@Override
			protected void populateItem(Item<AcademicSubject> item) {
				AcademicSubject academicSubject = item.getModelObject();
				item.add(new InvisibleLabel("id", academicSubject.getId()));
				item.add(new Label("name", academicSubject.getName()));

				item.add(new InvisibleLink<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new AcademicSubjectEditPage(academicSubject));
					}
				});

				item.add(new InvisibleLink<Void>("delete-link") {
					@Override
					public void onClick() {
						AcademicSubjectsPage page = new AcademicSubjectsPage();
						try {
							academicSubjectService.delete(academicSubject.getId());
							page.success("Subject removed.");
						} catch (PersistenceException e) {
							page.error("Error removing subject.");
						} finally {
							setResponsePage(page);
						}
					}
				});
			}
		};
		add(academicSubjectDataView);
		Label label = new Label("label-id", "ID");
		label.setVisible(AuthorizedSession.get().isSignedIn());
		add(label);		
	}	
	
	
	private class AcademicSubjectDataProvider extends SortableDataProvider<AcademicSubject, Serializable> {

		@Override
		public Iterator<AcademicSubject> iterator(long first, long count) {
			return academicSubjectService.getAll().iterator();
		}

		@Override
		public long size() {
			return academicSubjectService.getAll().size();
		}

		@Override
		public IModel<AcademicSubject> model(AcademicSubject object) {
			return new Model(object);
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
