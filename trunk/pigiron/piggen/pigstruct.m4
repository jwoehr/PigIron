divert(-1)
changecom(`\\\\')
\\ pigstruct.m4
\\   Macros to autogenerate Java code for PigIron Structure parameters
\\   Copyright *C* 1999, 2001, 2008 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com
include(`pigsty.m4')

\\ Stream definitions
define(`null_stream',         `-1')dnl        \\ The bit bucket
define(`normal_stream',        `0')dnl        \\ Standard output
define(`file_header_stream',  `92')dnl        \\ Stream on which we define file header
define(`package_stream',      `93')dnl        \\ Stream on which we call out package
define(`imports_stream',      `94')dnl        \\ Stream on which we call out imports
define(`class_header_stream', `95')dnl        \\ Stream on which we define class header
define(`constant_stream',     `97')dnl        \\ Stream on which we define static finals
define(`attribute_stream',    `98')dnl        \\ Stream on which we define attributes
define(`accessor_stream',    `100')dnl        \\ Stream on which we define accessors
define(`ctor_stream',        `110')dnl	      \\ Stream on which we define the ctors
define(`function_stream',    `120')dnl        \\ Stream on which we define functions
define(`model_stream',       `130')dnl	      \\ Stream on which we define modelFormalParameters
define(`class_footer_stream',`140')dnl        \\ Stream on which we define class footer
define(`file_footer_stream', `150')dnl        \\ Stream on which we define file footer

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigparm_import(`import_name')
define(`pigparm_import',`dnl
push_divert(imports_stream)dnl
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
push_divert(`package_stream')dnl
package x_package();

pop_divert()dnl
define(`myClass',x_name())dnl
define(`mySuperClass',x_extends())dnl
x_comment()
public class x_name() extends x_extends() {

popdef(`x_comment')dnl
popdef(`x_package')dnl
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
push_divert(attribute_stream)dnl
pushdef(`x_accessor', ifelse($1, `', `private ', $1 ))dnl
pushdef(`x_scope', ifelse($2, `', `', $2 ))dnl
pushdef(`x_type', $3)dnl
pushdef(`x_name', $4)dnl
pushdef(`x_initial_value', $5)dnl
pushdef(`x_set_accessor', ifelse($6, `', `public', $6))dnl
pushdef(`x_comment', $7)dnl
pushdef(`x_qualified_name', x_accessor()x_scope()x_type() x_name())dnl
pushdef(`x_get_accessor_name', ifelse(x_type, `boolean', `is_', `get_')`'x_name())dnl
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

popdef(`x_accessor')dnl
popdef(`x_scope')dnl
popdef(`x_type')dnl
popdef(`x_name')dnl
popdef(`x_initial_value')dnl
popdef(`x_set_accessor')dnl
popdef(`x_comment')dnl
popdef(`x_qualified_name')dnl
pop_divert()dnl
')

\\ pigparm_ctors
define(`pigparm_ctors',`dnl
push_divert(ctor_stream)dnl
    /**
     * Create an instance with a value derived by copying from a like instance
     * and instance its formal name at the same time.
     * null is legal value, means "just clear me and
     * re-initialize me with a valid list of yet-unread
     * parameters".
     * @param value a like instance to copy from
     * @param formalName the formal name
     * @see com.softwoehr.pigiron.access.mySuperClass()
     */
    public myClass()`('mySuperClass() value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public myClass()`('mySuperClass() value) {
        super(value);
        if (value == null) {
            modelFormalParameters();
        }
    }

    /**
     * Create a read-modelled instance.
     */
    public myClass()`()' {
        super();
        modelFormalParameters();
    }

pop_divert()dnl
')

// pigparm_model_start()
define(`pigparm_model_start',`dnl
push_divert(model_stream)dnl
    /**
     * Create a read-modelled instance.
     */
    public void modelFormalParameters() {
        clear();
pop_divert()dnl
')

// pigparm_model_parm(type, value, formal_name)
define(`pigparm_model_parm',`dnl
push_divert(model_stream)dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
ifelse(is_type_named_array(x_type),-1,`dnl
ifelse(x_type(),`CountedString',`dnl
        add`('new VSMInt4`('`-1', "x_formal_name()`_length'"`)'`)';
        add`('new VSMString`('x_value(), "x_formal_name()"));',`dnl
        add`('new x_type()(x_value(), "x_formal_name()"));')',`dnl
        add`('x_type()`.modelArray'`('"x_formal_name()"));
')
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
pop_divert()dnl
')

// pigparm_model_end()
define(`pigparm_model_end',`dnl
push_divert(model_stream)dnl
    }
pop_divert()dnl
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
    /**
     * 'x_accessor()` 'x_scope()` 'x_return_type()` 'x_name()`('x_arguments()`)
     *
     *'ifelse(x_return_type(), `void', `', ` @return ')`
     */
    'x_accessor()` 'x_scope()` 'x_return_type()` 'x_name()` ('x_arguments()`) {
        'ifelse(x_return_type(), `void', `', x_return_type() `result = ' x_return_init_expression()`;')`
	'ifelse(x_function_body(), `',
 	`/* ... TODO  */',
	x_function_body())`

        'ifelse(x_return_type(), `void', `', `return result;')`
    }

popdef(`x_function_body')dnl
popdef(`x_comment')dnl
popdef(`x_arguments')dnl
popdef(`x_name')dnl
popdef(`x_return_init_expression')dnl
popdef(`x_return_type')dnl
popdef(`x_scope')dnl
popdef(`x_accessor')dnl
pop_divert()dnl
')

\\ pigparm_endclass ... Close a class definition
define(`pigparm_endclass',`dnl
push_divert(class_footer_stream)dnl
}
push_divert(file_footer_stream)dnl

/* End of autogenerated code */

pop_divert()dnl
pop_divert()dnl
')

\\ Start definitions
define(`pigparm_start',`dnl
push_divert(file_header_stream)dnl
include(`pigiron_copyright_comment.txt')
`/* Autogenerated' date_string()` * by pigstruct.m4 Copyright *C* 2008 Jack J. Woehr'
` * Part of the PigIron Project http://pigiron.sourceforge.net'
` */'
pop_divert()dnl
')

\\ End definitions
define(`pigparm_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(package_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(ctor_stream)dnl
undivert(function_stream)dnl
undivert(model_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigstruct.m4
pop_divert()dnl
