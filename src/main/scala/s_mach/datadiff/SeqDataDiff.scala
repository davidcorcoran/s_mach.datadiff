/*
                    ,i::,
               :;;;;;;;
              ;:,,::;.
            1ft1;::;1tL
              t1;::;1,
               :;::;               _____       __  ___              __
          fCLff ;:: tfLLC         / ___/      /  |/  /____ _ _____ / /_
         CLft11 :,, i1tffLi       \__ \ ____ / /|_/ // __ `// ___// __ \
         1t1i   .;;   .1tf       ___/ //___// /  / // /_/ // /__ / / / /
       CLt1i    :,:    .1tfL.   /____/     /_/  /_/ \__,_/ \___//_/ /_/
       Lft1,:;:       , 1tfL:
       ;it1i ,,,:::;;;::1tti      s_mach.datadiff
         .t1i .,::;;; ;1tt        Copyright (c) 2014 S-Mach, Inc.
         Lft11ii;::;ii1tfL:       Author: lance.gatlin@gmail.com
          .L1 1tt1ttt,,Li
            ...1LLLL...
*/
package s_mach.datadiff

import scala.language.higherKinds
import s_mach.datadiff.impl.SeqDataDiffImpl

import scala.collection.generic.CanBuildFrom

/**
 * A reusable/extensible DataDiff implementation for sequences that is
 * implemented using https://code.google.com/p/java-diff-utils/ which is based
 * on http://www.xmailserver.org/diff2.pdf
 * @tparam A the value type
 * @tparam M the sequence type
 */
class SeqDataDiff[A,M[AA] <: Seq[AA]](implicit
  cbf:CanBuildFrom[Nothing, A, M[A]]
) extends SeqDataDiffImpl[A,M]
