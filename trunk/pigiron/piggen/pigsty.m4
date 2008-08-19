divert(-1)
changecom(`\\')
\\ pigsty.m4
\\   Macros commonly used to autogenerate Java code for PigIron
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

\\ A date stamp
define(`date_string',`esyscmd(/bin/date -u)')dnl
changecom()dnl
undivert()dnl