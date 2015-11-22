divert(-1)
changecom(`\\\\')
\\ fiji_pigfunc.m4
\\   Macros to autogenerate FIJI code for PigIron functions
\\   Copyright *C* 1999, 2001, 2008, 2015 Jack J. Woehr
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
push_divert(class_header_stream)dnl
pushdef(`x_name', $1)dnl
define(`myClassName',x_name())dnl
pushdef(`x_extends', $2)dnl
pushdef(`x_package', $3)dnl
pushdef(`x_function_name', $4)dnl
pushdef(`x_comment', $5)dnl
\ x_comment()\
popdef(`x_comment')dnl
popdef(`x_function_name')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ optional_ctor_params_comments(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_comments',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `
     * @param `$2' instances {@code `$3'}', `
     * @param `$2' instances {@code `$3'}`'optional_ctor_param_comments(shift(shift(shift($@))))')dnl
')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, ` ' ``$1'/`$2'', ` ' ``$1'/`$2'optional_ctor_param_args(shift(shift(shift($@))))')')

\\ process_ctor_param(type, name, instance, t, n, i ...)
define(`process_ctor_param', `eval(eval($# / 3) + 2) roll ifelse($1,`int',`Long>intparam') , ')

\\ optional_ctor_param_instantiations(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_instantiations',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `dnl
process_ctor_param($@)dnl
', `process_ctor_param($@)dnl
optional_ctor_param_instantiations(shift(shift(shift($@))))')dnl
')

\\ required_param_instantiations(count)
define(`required_param_instantiations',`dnl
eval($1+7) roll `,' eval($1+6) roll Long>intparam `,' eval($1+5) roll `,' eval($1+4) roll `,' eval($1+3) roll `,'')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`dnl
push_divert(ctor_stream)dnl
pushdef(`x_optional_params', `$@')dnl
pushdef(`x_optional_param_count', eval($# / 3))
\ Create an instance of the myClassName() function call with VSMAPI params instanced.\

: myClassName() \ String/hostname int/port String/userid String/password String/target_identifier `'optional_ctor_param_args(x_optional_params) -- instance \
com.softwoehr.pigiron.functions.myClassName() class new dnl
`(' required_param_instantiations(x_optional_param_count) optional_ctor_param_instantiations(x_optional_params) `)'
;
dnl         this`(');
dnl         setHostname`('hostname);
dnl         setPort`('port);
dnl         setUserid`('userid);
dnl         setPassword`('password);
dnl         setTarget_identifier`('target_identifier);
dnl optional_ctor_param_instantiations(x_optional_params())    }
dnl 
popdef(`x_optional_param_count')dnl
popdef(`x_optional_params')dnl
pop_divert()dnl
')

\\ pigfunc_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigfunc_constant',`dnl
push_divert(constant_stream)dnl
    \ (java $2) function_classname().$3 $5 \
    value function_classname().$3
    $4 to function_classname().$3
pop_divert()dnl
')

\\ pigfunc_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment')
define(`pigfunc_attribute',`')

\\ javadoc_args(arg1, arg2, ... argn)
define(`javadoc_args',`dnl
ifelse($#, 0, , $#, 1, `
    * @param `$1'', `javadoc_args(shift($@)')dnl
')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`')

\\ Close a class definition
define(`pigfunc_endclass',`dnl
push_divert(file_footer_stream)dnl

`\ End of autogenerated code \'

pop_divert()dnl
')

\\ Start definitions
define(`pigfunc_start',`dnl
push_divert(file_header_stream)dnl

`\ FIJI support for PigIron' function_classname `\'
`\ Autogenerated' date_stamp `by fiji_pigfunc.m4 \'
`\ Copyright *C* 2015 Jack J. Woehr \'
`\ Part of the PigIron Project http://pigiron.sourceforge.net \'

pop_divert()dnl
')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`')

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`')

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
undefine(`myClassName')dnl
')

\\ End of pigfunc.m4
pop_divert()dnl
