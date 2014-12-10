package s_mach.datadiff

/**
 * A type class for computing the differences between two instances of a type
 * @tparam A the type to compute differences on
 * @tparam P a type for a "patch" which represents the differences between any
 *           two instances of A
 */
trait DataDiff[A,P] {
  type Patch = P

  /**
   * Compute the difference between two values. Result is a patch that if
   * applied to the original value results in the new value.
   * @param oldValue the original value
   * @param newValue the new value
   * @return If oldValue and newValue are different, Some(patch) otherwise,
   *         if oldValue and newValue are the same, None
   */
  def calcDiff(oldValue: A, newValue: A) : Option[P]

  /**
   * Apply a patch (generated by a prior call to calcDiff) to a value
   * @param value the value to apply the patch to
   * @param patch the patch to apply
   * @return the new value with the patch applied
   */
  def applyPatch(value: A, patch: P) : A

}
