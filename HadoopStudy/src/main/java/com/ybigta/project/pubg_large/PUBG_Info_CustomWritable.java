package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PUBG_Info_CustomWritable implements Writable {
    private Text player_dist_ride;
    private Text player_dist_walk;
    private Text player_dmg;
    private Text player_kills;
    private Text player_survive_time;


    //constructor
    public PUBG_Info_CustomWritable(){
        set( new Text(), new Text(), new Text(), new Text(), new Text());
    }
    public PUBG_Info_CustomWritable(Text player_dist_ride, Text player_dist_walk, Text player_dmg, Text player_kills, Text player_survive_time, Text team_placement){
        set(player_dist_ride, player_dist_walk, player_dmg, player_kills, player_survive_time);
    }
    public PUBG_Info_CustomWritable(String player_dist_ride, String player_dist_walk, String player_dmg, String player_kills, String player_survive_time){
        set(new Text(player_dist_ride), new Text(player_dist_walk), new Text(player_dmg), new Text(player_kills), new Text(player_survive_time));
    }
    public PUBG_Info_CustomWritable(float player_dist_ride, float player_dist_walk, float player_dmg, float player_kills, float player_survive_time){
        set(new Text(String.valueOf(player_dist_ride)), new Text(String.valueOf(player_dist_walk)), new Text(String.valueOf(player_dmg)), new Text(String.valueOf(player_kills)), new Text(String.valueOf(player_survive_time)));
    }

    //setter
    public void set(Text player_dist_ride, Text player_dist_walk, Text player_dmg, Text player_kills, Text player_survive_time) {
        this.player_dist_ride = player_dist_ride;
        this.player_dist_walk = player_dist_walk;
        this.player_dmg = player_dmg;
        this.player_kills = player_kills;
        this.player_survive_time = player_survive_time;
    }
    public void set(String player_dist_ride, String player_dist_walk, String player_dmg, String player_kills, String player_survive_time) {
        this.player_dist_ride = new Text(player_dist_ride);
        this.player_dist_walk = new Text(player_dist_walk);
        this.player_dmg = new Text(player_dmg);
        this.player_kills = new Text(player_kills);
        this.player_survive_time = new Text(player_survive_time);
    }
    public void set(float player_dist_ride, float player_dist_walk, float player_dmg, float player_kills, float player_survive_time) {
        this.player_dist_ride = new Text(String.valueOf(player_dist_ride));
        this.player_dist_walk = new Text(String.valueOf(player_dist_walk));
        this.player_dmg = new Text(String.valueOf(player_dmg));
        this.player_kills = new Text(String.valueOf(player_kills));
        this.player_survive_time = new Text(String.valueOf(player_survive_time));
    }

    //getter
    public Text getPlayer_dist_ride() {
        return player_dist_ride;
    }
    public Text getPlayer_dist_walk() {
        return player_dist_walk;
    }
    public Text getPlayer_dmg() {
        return player_dmg;
    }
    public Text getPlayer_kills() {
        return player_kills;
    }
    public Text getPlayer_survive_time() {
        return player_survive_time;
    }


    @Override
    public void readFields(DataInput dataInput) throws IOException {
        player_dist_ride.readFields(dataInput);
        player_dist_walk.readFields(dataInput);
        player_dmg.readFields(dataInput);
        player_kills.readFields(dataInput);
        player_survive_time.readFields(dataInput);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        player_dist_ride.write(dataOutput);
        player_dist_walk.write(dataOutput);
        player_dmg.write(dataOutput);
        player_kills.write(dataOutput);
        player_survive_time.write(dataOutput);
    }

    @Override
    public String toString() {
        return "|" + player_dist_ride + "|" + player_dist_walk + "|" + player_dmg + "|" + player_kills + "|" + player_survive_time;
    }
}
