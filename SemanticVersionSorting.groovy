 // Call this function using below commented line
 // def List<String> versionsToKeepList = mostRecentVersion(fillVersions,numToKeep)


List<String> mostRecentVersion(List versions,int numbersToKeep) {
if (versions ==null || versions.empty)
{
return versions
}else{

  def List<String> sorted = versions.sort(false) { a, b -> 

    List verA = a.tokenize('.')
    List verB = b.tokenize('.')

    def commonIndices = Math.min(verA.size(), verB.size())

    for (int i = 0; i < commonIndices; ++i) {
      def numA = verA[i].toInteger()
      def numB = verB[i].toInteger()
      
      if (numA != numB) {
        return numA <=> numB
      }
    }

    // If we got this far then all the common indices are identical, so whichever version is longer must be more recent
    verA.size() <=> verB.size()
  }

   Collections.reverse(sorted);
  
  sorted[-1]
  sorted.removeAll(Arrays.asList([null],""))
  sorted.unique()
  def sortedMajor = []
  sorted.each {
  String[] semversion = it.tokenize('.')
  String majorVersion = semversion[0]; 
  sortedMajor  << majorVersion

}
 
def sortedMajorUniqueVersions= sortedMajor.unique()
 
def verToKeep =[]
  
sortedMajorUniqueVersions.each {
    
def String versionToPass = it
def startwithparam = sorted.findAll { y -> y.startsWith(versionToPass)}
def top5values = startwithparam.take(numbersToKeep)
verToKeep.addAll(top5values)
}
return verToKeep
}

 }
