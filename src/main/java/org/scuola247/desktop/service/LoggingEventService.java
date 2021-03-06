package org.scuola247.desktop.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.scuola247.desktop.entity.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResult;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
//import javax.persistence.EntityManager;

@Service
public class LoggingEventService {

	private static final ImmutableMap<String, String> mapGuiColumn2DbField = new ImmutableMap.Builder<String, String>()
			.put("dateTime", "timestmp").put("message", "formattedMessage").put("level", "levelString").build();

//	@PersistenceContext
//	private EntityManager entityManager;

	@ExtDirectMethod(STORE_READ)
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('Gestore')")
	public ExtDirectStoreResult<org.scuola247.desktop.dto.LoggingEvent> read(ExtDirectStoreReadRequest request) {
/*
		JPQLQuery query = new JPAQuery(entityManager).from(QLoggingEvent.loggingEvent);

		if (!request.getFilters().isEmpty()) {
			StringFilter levelFilter = (StringFilter) request.getFilters().iterator().next();
			String levelValue = levelFilter.getValue();
			query.where(QLoggingEvent.loggingEvent.levelString.eq(levelValue));
		}

		QueryUtil.addPagingAndSorting(query, request, LoggingEvent.class, QLoggingEvent.loggingEvent,
				mapGuiColumn2DbField, Collections.<String> emptySet());

		SearchResults<LoggingEvent> searchResult = query.listResults(QLoggingEvent.loggingEvent);
*/
		
		Set<LoggingEvent> searchResult = new HashSet<>();
		
		List<org.scuola247.desktop.dto.LoggingEvent> loggingEventList = Lists.newArrayList();
		for (LoggingEvent event : searchResult) {
			loggingEventList.add(new org.scuola247.desktop.dto.LoggingEvent(event));
		}
		return new ExtDirectStoreResult<>(loggingEventList.size(), loggingEventList);
	}

	@ExtDirectMethod
	@Transactional
	@PreAuthorize("hasRole('Gestore')")
	public void deleteAll(String level) {
		/*
		if (StringUtils.hasText(level)) {
			for (org.scuola247.desktop.entity.LoggingEvent le : new JPAQuery(entityManager).from(QLoggingEvent.loggingEvent)
					.where(QLoggingEvent.loggingEvent.levelString.eq(level)).list(QLoggingEvent.loggingEvent)) {
				entityManager.remove(le);
			}
		} else {
			for (org.scuola247.desktop.entity.LoggingEvent le : new JPAQuery(entityManager).from(QLoggingEvent.loggingEvent)
					.list(QLoggingEvent.loggingEvent)) {
				entityManager.remove(le);
			}
		}
		*/
	}

	@ExtDirectMethod
	@PreAuthorize("hasRole('Gestore')")
	public void addTestData() {
		Logger logger = LoggerFactory.getLogger(getClass());

		logger.debug("a simple debug log entry");
		logger.info("this is a info log entry");
		logger.warn("a warning", new IllegalArgumentException());
		logger.error("a serious error", new NullPointerException());
	}

}
