package org.hadooplab;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.conf.*;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;

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
        if (args.length != 2) {
                System.err.println("Usage: WordCount needs two arguments <input> <output> files");
                System.exit(-1);
        }


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

        int res = job.waitForCompletion(true) ? 0 : 1;
	if(job.isSuccessful()) {
        	System.out.println("Job was successful");
        } else if(!job.isSuccessful()) {
                System.out.println("Job was not successful");            
        }
        System.exit(res);
    }
}
