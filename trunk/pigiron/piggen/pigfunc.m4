divert(-1)
changecom(`\\')
\\ pigfunc.m4
\\   Macros to autogenerate Java code for PigIron parameters
\\   Copyright *C* 1999, 2001, 2008 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com

\\ Diversion prepared for restoration
\\ USAGE: push_divert(streamName)
\\ DEFINES: `temp_diversion'
define(`push_divert',`dnl
pushdef(`temp_diversion', divnum)dnl
divert($1)dnl
')

\\ Restore previous pushed diversion
\\ USAGE: pop_divert()
\\ DEPENDS: `temp_diversion'
define(`pop_divert',`dnl
divert(temp_diversion)dnl
popdef(`temp_diversion')dnl
')

\\ Stream definitions
define(`normal_stream',       `0')        \\ Standard output
define(`file_header_stream',  `2')        \\ Stream on which we define file header
define(`file_footer_stream',  `3')        \\ Stream on which we define file footer
define(`imports_stream',      `4')        \\ Stream on which we call out imports
define(`class_header_stream', `5')        \\ Stream on which we define class header
define(`class_footer_stream', `6')        \\ Stream on which we define class footer
define(`constant_stream',     `7')        \\ Stream on which we define static finals
define(`attribute_stream',    `8')        \\ Stream on which we define attributes
define(`function_stream',     `9')        \\ Stream on which we define functions
define(`accessor_stream',    `10')        \\ Stream on which we define accessors
define(`ctor_stream',        `16')        \\ Stream on which we define ctor
define(`ctor_body_stream',   `18')        \\ Stream on which we define ctor {body}
define(`copy_ctor_stream',   `20')        \\ Stream on which we define copy ctor without formal name instancing
define(`copy_name_ctor_stream',   `28')   \\ Stream on which we define copy ctor with formal name instancing

define(`null_stream',        `-1')        \\ The bit bucket

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigparm_import(`import_name')
define(`pigparm_import',`dnl
push_divert(`imports_stream')dnl
import $1;
pop_divert()dnl
')

\\ pigparm_class(`name',`extends',`package',`comment')
define(`pigparm_class',`dnl
push_divert(class_header_stream)dnl
pushdef(`x_name', $1)dnl
pushdef(`x_extends', $2)dnl
pushdef(`x_package', $3)dnl
pushdef(`x_comment', $4)dnl

package x_package();
x_comment()
     public class x_name() extends x_extends() {

popdef(`x_comment', $2)dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ pigparm_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigparm_constant',`dnl
push_divert(constant_stream)dnl
    `/**' $5 `*/'
     $1 `static final' $2 $3 `=' $4`;'

pop_divert()dnl
')

\\ pigparm_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment'
define(`pigparm_attribute',`dnl
pushdef(`x_accessor', ifelse($1, `', `private ', $1 ))dnl
pushdef(`x_scope', ifelse($2, `', `', $2 ))dnl
pushdef(`x_type', $3)dnl
pushdef(`x_name', $4)dnl
pushdef(`x_initial_value', $5)dnl
pushdef(`x_set_accessor', ifelse($6, `', `public', $6))dnl
pushdef(`x_comment', $7)dnl
pushdef(`x_qualified_name', x_accessor()x_scope()x_type() x_name())dnl
pushdef(`x_get_accessor_name', ifelse(x_type, `boolean', `is_', `get_')`'x_name())dnl
push_divert(attribute_stream)dnl
    `/**' x_comment() `*/'
    x_qualified_name() `=' x_initial_value()`;'

pop_divert()dnl
push_divert(accessor_stream)dnl
    `/** Set the value of' x_qualified_name() `.'
    `  * @param val The value to set' x_qualified_name() `.'
    `  */'
    x_set_accessor() void `set_'x_name() `('x_type() `val'`) {'
        x_name() = `val;'
    `}'

    `/** Get the value of' x_qualified_name() `.'
    `  * @return The value of' x_qualified_name() `.'
    `  */'
    public x_type() x_get_accessor_name() `() {'
        return x_name()`;'
    `}'

pop_divert()dnl
popdef(`x_acessor')dnl
popdef(`x_scope')dnl
popdef(`x_type')dnl
popdef(`x_name')dnl
popdef(`x_initial_value')dnl
popdef(`x_set_accessor')dnl
popdef(`x_comment')dnl
popdef(`x_qualified_name')dnl
')

\\ pigparm_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `comment', `function_body')
define(`pigparm_function',`dnl
pushdef(`x_accessor', $1)dnl
pushdef(`x_scope', ifelse($2, `', ` ', $2 ))dnl
pushdef(`x_return_type', $3)dnl
pushdef(`x_return_init_expression', $4)dnl
pushdef(`x_name', $5)dnl
pushdef(`x_arguments', `$6')dnl
pushdef(`x_comment', $7)dnl
pushdef(`x_function_body', $8)dnl
push_divert(function_stream)dnl
    `/**' x_accessor() x_scope()`'x_return_type() x_name `('x_arguments()`)'
    `  *'
    `  *'
    `  *'ifelse(x_return_type(), `void', `', ` @return ')
    `  */'
    x_accessor() x_scope()`'x_return_type() x_name() `('x_arguments()`) {'
        ifelse(x_return_type(), `void', `', x_return_type() `result = ' x_return_init_expression()`;')
	ifelse(x_function_body(), `',
 	`/* ... TODO  */',
	x_function_body())

        ifelse(x_return_type(), `void', `', `return result;')
    `}'

pop_divert()dnl
popdef(`x_accessor')dnl
popdef(`x_scope')dnl
popdef(`x_return_type')dnl
popdef(`x_name')dnl
popdef(`x_arguments')dnl
popdef(`x_comment')dnl
')

\\ Close a class definition
define(`pigparm_endclass',`dnl
push_divert(class_footer_stream)dnl

}
pop_divert()dnl
')

\\ Start definitions
define(`pigparm_start',`dnl
push_divert(file_header_stream)dnl
include(`pigiron_copyright_comment.txt')
`/* Autogenerated by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr */'
`/*  Part of the PigIron Project http://pigiron.sourceforge.net  */'
`'
pop_divert()dnl
')

\\ Here's some stuff for composeInputArray();
\\	ifelse(x_type(),`CountedString',`dnl
\\	ifdef(`myTempString',`',define(`myTempString',`tempString')dnl
\\	String myTempString() = new String("value");

\\ End definitions
define(`pigparm_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(function_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigfunc.m4
pop_divert()dnl
