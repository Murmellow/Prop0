/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

public interface INode {

	Object evaluate(Object[] args) throws Exception;

	void buildString(StringBuilder builder, int tabs);
}