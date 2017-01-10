package org.hadooplab;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.conf.*;
import java.util.*;

public class WordCount {

    public static class Map extends Mapper</*?*/, /*?*/, /*?*/, /*?*/> {

        @Override
        public void map(/*?*/ key, /*?*/ value, Context context)
               throws IOException, InterruptedException {
            // Write me
        }
    }

    public static class Reduce extends Reducer</*?*/, /*?*/, /*?*/, /*?*/> {

        @Override
        public void reduce(/*?*/ key, /*?*/ values, Context context)
                throws IOException, InterruptedException {
            // Write me
        }
    }

    public static void main(String[] args) throws Exception {

        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(WordCount.class);

        job.setOutputKeyClass(/*?*/);
        job.setOutputValueClass(/*?*/);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}

