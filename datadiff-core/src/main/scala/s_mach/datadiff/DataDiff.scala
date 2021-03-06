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

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/**
 * A type class for computing the differences between two instances of a type
 * @tparam A the type to compute differences on
 * @tparam P a type for a "patch" which represents the differences between any
 *           two instances of A
 */
trait DataDiff[A,P] {
  type Patch = P

  /** The value of P that represents no change */
  val noChange : P

  /**
   * Compute the difference between two values. Result is a patch that if
   * applied to the original value results in the new value.
   * @param oldValue the original value
   * @param newValue the new value
   * @return If oldValue and newValue are different, P (that is not equal to
   *         noChange). Otherwise, noChange
   */
  def calcDiff(oldValue: A, newValue: A) : P

  /**
   * Apply a patch (generated by a prior call to calcDiff) to a value. If patch
   * is equal to noChange, then value is returned unmodified.
   * @param value the value to apply the patch to
   * @param patch the patch to apply
   * @return the new value with the patch applied
   */
  def applyPatch(value: A, patch: P) : A

}

object DataDiff {
  /**
   * Generate a DataDiff implementation for a product type
   * @tparam A the value type
   * @tparam P the patch type for the value type
   * @return the DataDiff implementation
   */
  def forProductType[A <: Product, P <: Product] : DataDiff[A,P] =
    macro macroForProductType[A,P]

  // Note: Scala requires this to be public
  def macroForProductType[A:c.WeakTypeTag,P:c.WeakTypeTag](
    c: blackbox.Context
  ) : c.Expr[DataDiff[A,P]] = {
    val builder = new impl.DataDiffMacroBuilderImpl(c)
    builder.build[A,P].asInstanceOf[c.Expr[DataDiff[A,P]]]
  }
}

