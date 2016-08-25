package ${package}.dao;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

@DB
public interface TestDao {

	@SQL("SELECT COUNT(1) FROM parameter")
	public int selctCount();
}
