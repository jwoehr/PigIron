divert(-1)
changecom(`\\\\')
\\ smapihelper_pigfunc.m4
\\   Macros to autogenerate Java code for Ublu SmapiHelper class in to access PigIron functions
\\   Copyright *C* 2008, 2015 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com
include(`pigsty.m4')

\\ Stream definitions
define(`null_stream',           `-1')dnl        \\ The bit bucket
define(`normal_stream',          `0')dnl        \\ Standard output
define(`file_header_stream',    `92')dnl        \\ Stream on which we define file header
define(`package_stream',        `93')dnl        \\ Stream on which we call out package
define(`imports_stream',        `94')dnl        \\ Stream on which we call out imports
define(`class_header_stream',   `95')dnl        \\ Stream on which we define class header
define(`constant_stream',       `97')dnl        \\ Stream on which we define static finals
define(`attribute_stream',      `98')dnl        \\ Stream on which we define attributes
define(`accessor_stream',      `100')dnl        \\ Stream on which we define accessors
define(`ctor_stream',          `110')dnl        \\ Stream on which we define ctor
define(`function_stream',      `120')dnl        \\ Stream on which we define functions
define(`compose_in_stream',    `130')dnl        \\ Stream on which we define composeInput
define(`compose_out_stream',   `135')dnl        \\ Stream on which we define composeInput
define(`get_function_name_stream', `139')dnl    \\ Stream on which we define getFunctionName()
define(`class_footer_stream',  `140')dnl        \\ Stream on which we define class footer
define(`file_footer_stream',   `150')dnl        \\ Stream on which we define file footer

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigfunc_import(`import_name')
define(`pigfunc_import',`')

\\ pigfunc_class(`name',`extends',`package',`function_name',`comment')
define(`pigfunc_class',`dnl
define(`myClassName',x_name())dnl
')

\\ optional_ctor_params_comments(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_comments',`')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`')

\\ optional_ctor_param_instantiations(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_instantiations',`')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`')

\\ pigfunc_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigfunc_constant',`')

\\ pigfunc_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment')
define(`pigfunc_attribute',`')

\\ javadoc_args(arg1, arg2, ... argn)
define(`javadoc_args',`')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`')

\\ Close a class definition
define(`pigfunc_endclass',`dnl
push_divert(class_footer_stream)dnl
            }
	    break;
pop_divert()dnl
')

\\ Start definitions
define(`pigfunc_start',`')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`dnl
push_divert(`compose_in_stream')dnl
            case "function_formal_name()": {
dnl                String target_identifier = argArray.nextMaybeQuotationTuplePopString`('`)';
pop_divert()dnl
push_divert(function_stream)dnl
                resultParameterArray
                        = new javaize(function_formal_name())`('hostname, port, userid, password`'dnl
pop_divert()dnl
')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`dnl
push_divert(`compose_in_stream')dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
ifelse(x_type(),`CountedString',`dnl
                String x_formal_name = argArray.nextMaybeQuotationTuplePopString`('`)';
',`dnl
                int x_formal_name = argArray.nextIntMaybeQuotationTuplePopString`('`)';
')dnl
pop_divert()dnl
push_divert(function_stream)dnl
, x_formal_name`'dnl
pop_divert()dnl
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`')
)

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`dnl
push_divert(function_stream)dnl
`)'
                         .doIt`('use_ssl`)';
pop_divert()dnl
')

\\ pigfunc_compose_output_start()
define(`pigfunc_compose_output_start',`')

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ pigfunc_compose_output_parm(type, value, formal_name)
define(`pigfunc_compose_output_parm',`')

\\ pigfunc_compose_output_end()
define(`pigfunc_compose_output_end',`')

\\ End definitions
define(`pigfunc_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(package_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(ctor_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(compose_in_stream)dnl
undivert(compose_out_stream)dnl
undivert(get_function_name_stream)dnl
undivert(function_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigfunc.m4
pop_divert()dnl
