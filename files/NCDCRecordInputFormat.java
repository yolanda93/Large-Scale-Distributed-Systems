package org.hadooplab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class NCDCRecordInputFormat extends TextInputFormat {

    public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) {
        return new NCDCRecordReader();
    }

    public class NCDCRecordReader extends RecordReader<LongWritable, Text> {

        private BufferedReader in;
        private long start, end;
        private LongWritable currentKey = new LongWritable();
        private Text currentValue = new Text();

        // ...

        @Override
        public void initialize(InputSplit split, TaskAttemptContext context)
               throws IOException, InterruptedException {

           String line;
           Configuration job = context.getConfiguration();

           // Open the file.
           FileSplit fileSplit = (FileSplit)split;
           Path file = fileSplit.getPath();
           FileSystem fs = file.getFileSystem(job);
           FSDataInputStream is = fs.open(file);
           in = new BufferedReader(new InputStreamReader(is));

           // Find the beginning and the end of the split.
           start = fileSplit.getStart();
           end = start + fileSplit.getLength();

           // ...

           // TODO: write the rest of the function. It will initialize needed
           // variables, move to the right position in the file, and start
           // reading if needed.
       }

       @Override
       public boolean nextKeyValue() throws IOException, InterruptedException {
           // TODO: read the next key/value, set the key and value variables
           // to the right values, and return true if there are more key and
           // to read. Otherwise, return false.
       }

      @Override
       public void close() throws IOException {
           in.close();
       }

       @Override
       public LongWritable getCurrentKey() throws IOException, InterruptedException {
           return currentKey;
       }

       @Override
       public Text getCurrentValue() throws IOException, InterruptedException {
           return currentValue;
       }

       @Override
       public float getProgress() throws IOException, InterruptedException {
           // TODO: calculate a value between 0 and 1 that will represent the
           // fraction of the file that has been processed so far.
       }
   }
}

