import util.chaining.*

def run(data: loadData.Parsed): Int = {
  minpoint
    .run(data)
    .map(x => x._1 + 1)
    .sum
}
