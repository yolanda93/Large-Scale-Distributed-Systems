package org.hadooplab;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
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

public class EulersConstant extends Configured implements Tool {

    static private final Path TMP_DIR = new Path(EulersConstant.class.getSimpleName() + "_tmp");

    public static class EMapper extends Mapper</*?*/, /*?*/, /*?*/, /*?*/> {

        @Override
        public void map(/*?*/ seed, /*?*/ size, Context context)
                throws IOException, InterruptedException {
            // TODO: write me
        }
    }

    public static class EReducer extends Reducer</*?*/, /*?*/, /*?*/, /*?*/> {

        @Override
        public void reduce(/*?*/ n, Iterable</*?*/> ts, Context context)
                throws IOException, InterruptedException {
            // TODO: write me
        }
    }

    public static BigDecimal estimate(int numMaps, long numPoints, Job job)
            throws IOException, ClassNotFoundException, InterruptedException {

        // Job parameters
        job.setJarByClass(EulersConstant.class);

        job.setMapperClass(EMapper.class);
        job.setReducerClass(EReducer.class);

        job.setMapOutputKeyClass(/*?*/);
        job.setMapOutputValueClass(/*?*/);
        job.setOutputKeyClass(/*?*/);
        job.setOutputValueClass(/*?*/);

        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setNumReduceTasks(1);

        // Input and output paths
        Path inDir = new Path(TMP_DIR, "in");
        Path outDir = new Path(TMP_DIR, "out");
        FileInputFormat.setInputPaths(job, inDir);
        FileOutputFormat.setOutputPath(job, outDir);

        // Create the temporary directory in which the input and output files are stored
        FileSystem fs = FileSystem.get(job.getConfiguration());
        if (fs.exists(TMP_DIR)) {
            throw new IOException("Temporary directory " + fs.makeQualified(TMP_DIR)
                    + " already exists.  Please remove it first.");
        }
        if (!fs.mkdirs(inDir)) {
            throw new IOException("Cannot create input directory " + inDir);
        }

        // TODO: Generate one file for each map

        // Start the job
        System.out.println("Starting Job");
        long startTime = System.currentTimeMillis();
        job.waitForCompletion(true);
        double duration = (System.currentTimeMillis() - startTime) / 1000.0;
        System.out.println("Job Finished in " + duration + " seconds");

        // Read the results and compute the value of Euler's constant
        SequenceFile.Reader reader =
            new SequenceFile.Reader(job.getConfiguration(), SequenceFile.Reader.file(new Path(TMP_DIR, "out/part-r-00000")));
        // TODO: Compute and return the result
    }

    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: " + getClass().getName() + " <nMaps> <nSamples>");
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        int nMaps = Integer.parseInt(args[0]);
        long nSamples = Long.parseLong(args[1]);

        System.out.println("Number of Maps  = " + nMaps);
        System.out.println("Samples per Map = " + nSamples);

        Job job = Job.getInstance(new Configuration());
        System.out.println("Estimated value of Euler's Constant = "
                           + estimate(nMaps, nSamples, job));
        return 0;
    }

    public static void main(String[] argv) throws Exception {
        System.exit(ToolRunner.run(null, new EulersConstant(), argv));
    }
}

