WordCount: WordCount.java
	javac -cp "/net/home/j/johan1//hadoop-2.7.2/share/hadoop/common/*:/net/home/j/johan1/hadoop-2.7.2/share/hadoop/mapreduce/*" WordCount.java

run: WordCount.class
	java -cp "/net/home/j/johan1//hadoop-2.7.2/share/hadoop/common/*:/net/home/j/johan1/hadoop-2.7.2/share/hadoop/mapreduce/*:/net/home/j/johan1//hadoop-2.7.2/share/hadoop/common/lib/*:/net/home/j/johan1//hadoop-2.7.2/share/hadoop/yarn/*:." WordCount input.txt output

clean:
	rm -f *.class
	rm -rf output

