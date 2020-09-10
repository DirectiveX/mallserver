package com.jiahao.resources.sever;

import com.jiahao.resources.sever.dao.*;
import com.jiahao.resources.sever.dto.*;
import com.jiahao.resources.sever.service.GoodService;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerTest {

    private static final String GOOD_URL = "http://localhost:8092/goodpic/";
    private static final String DETAIL_URL = "http://localhost:8092/gooddetailpic/";
    private static final String HOME_URL = "http://localhost:8092/home/";
    private static final String SCULPTURE_URL = "http://localhost:8092/sculpture/";
    private static final String USER_SCULPTURE_URL = "http://localhost:8092/userheadscuplture/";
    private static final String USER_COMMENT_PIC_URL = "http://localhost:8092/commentpics/";

    private char [] chars = {'和','发','过','给','最','就','有','在','吧','你','好'};
    private String [] size = {"S","X","XL","XXL","3XL","M"};
    private String [] length = {"123","321","241","41","52","56"};
    private String [] season = {"春季","夏季","秋季","冬季"};
    private String [] material = {"麻","皮革","涤纶","羊毛","丝绸","棉布","呢绒","蚕丝","晴纶","真丝"};
    private String [] type = {"毛衣","衬衣","半袖","外套","羽绒服","西服","胸罩","裘皮","马夹","T恤","背心"};
    private String [] style = {"瑞丽","嘻皮","百搭","淑女","韩版","民族","欧美","学院","通勤","中性","嘻哈","田园","朋克","OL","洛丽塔","街头","简约","波西米亚"};
    private String [] fashion = {"THETHING","lilbetter","StayReal","Begins","闪电"};
    private String [] color = {"蓝","红","绿","黑","橘"};
    private Map<Integer,String> map = new HashMap<Integer,String>(){{put(0,"pop");put(1,"news");put(2,"sell");}};

    @Autowired
    private GoodService goodService;
    @Autowired
    private DetailDao detailDao;
    @Autowired
    private SafeguardDao safeguardDao;
    @Autowired
    private ShopKeeperDao shopKeeperDao;
    @Autowired
    private DetailPicDao detailPicDao;
    @Autowired
    private DescriptionPicDao descriptionPicDao;
    @Autowired
    private ParamDao paramDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentPicDao commentPicDao;

    @Test
    public void test(){
        List<Detail> detailList = new ArrayList<>();
        List<Safeguard> safeguards = new ArrayList<>();
        List<Good> goods = new ArrayList<>();
        List<DetailPic> pics = new ArrayList<>();
        int initShopKeeperImg = 115;
        ShopKeeper shopKeeper = new ShopKeeper("ShopKeeper",initShopKeeperImg*8,initShopKeeperImg*9,SCULPTURE_URL+ (initShopKeeperImg++) +".png");
        for (int i = 0;i < 114;i ++) {
            Good good = new Good(randomString(),HOME_URL + i +".jpg",i * 10,map.get(i%3),"https://www.bilibili.com",i * 10.81);
            goods.add(good);
            if(i%3 == 2){
//                shopKeeper.setDetails(detailList);
                shopKeeperDao.save(shopKeeper);
                goodService.saveAll(goods);
                safeguardDao.saveAll(safeguards);
                detailDao.saveAll(detailList);
                detailPicDao.saveAll(pics);
                shopKeeper = new ShopKeeper("ShopKeeper",i*8,i*9,SCULPTURE_URL+ (initShopKeeperImg++) +".png");
                goods = new ArrayList<>();
                detailList = new ArrayList<>();
                safeguards = new ArrayList<>();
                pics = new ArrayList<>();
            }
            Detail detail = new Detail("title"+ i,i,i * 11.38,1.88 * i%5,1.68 * i%5,1.45 * i%5,"description"+i);
            pics.add(new DetailPic(GOOD_URL+ (i+1) +".png",detail));
            pics.add(new DetailPic(GOOD_URL+ (i+2) +".png",detail));
            pics.add(new DetailPic(GOOD_URL+ (i+3) +".png",detail));
            detailList.add(detail);
            Safeguard safeguard = new Safeguard("safeguard"+i);
            safeguard.setDetails(detailList);
            detail.setSafeguards(safeguards);
            detail.setGood(good);
            detail.setShopKeeper(shopKeeper);
            safeguards.add(safeguard);
        }
        shopKeeperDao.save(shopKeeper);
        goodService.saveAll(goods);
        safeguardDao.saveAll(safeguards);
        detailDao.saveAll(detailList);
        detailPicDao.saveAll(pics);
    }


    @Test
    public void addDescriptionPic(){
        List<Detail> all = detailDao.findAll();
        List<DescriptionPic> pics = new ArrayList<>();

        if(all != null && all.size() > 0){
            for(int i = 0;i < all.size();i ++){
                pics.add(new DescriptionPic(DETAIL_URL+ 4*i+".jpg",all.get(i)));
                pics.add(new DescriptionPic(DETAIL_URL+ (4*i + 1) +".jpg",all.get(i)));
                pics.add(new DescriptionPic(DETAIL_URL+ (4*i + 2) +".jpg",all.get(i)));
                pics.add(new DescriptionPic(DETAIL_URL+ (4*i + 3) +".jpg",all.get(i)));
            }
        }
        descriptionPicDao.saveAll(pics);
    }

    @Test
    public void addCommentInfo(){
        List<Detail> all = detailDao.findAll();
        List<Comment> comments = new ArrayList<>();
        List<CommentPic> commentPics = new ArrayList<>();

        if(all != null && all.size() > 0){
            for(int i = 0;i < all.size();i ++){
                Comment comment1 = new Comment("username" + 4 * i, USER_SCULPTURE_URL + (4 * i) % 134 + ".jpg", "comment detail #" + 4 * i, new Date(), color[i % 5], all.get(i));
                Comment comment2 = new Comment("username"+ (4*i + 1),USER_SCULPTURE_URL+ (4*i + 1)%134+".jpg","comment detail #"+(4*i + 1), new Date(), color[i % 5], all.get(i));
                Comment comment3 = new Comment("username"+ (4*i + 2),USER_SCULPTURE_URL+ (4*i + 2)%134+".jpg","comment detail #"+(4*i + 2), new Date(), color[i % 5], all.get(i));
                Comment comment4 = new Comment("username" + (4 * i + 3), USER_SCULPTURE_URL + (4 * i + 3) % 134 + ".jpg", "comment detail #" + (4 * i + 3), new Date(), color[i % 5], all.get(i));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i) % 157 + ".jpg",comment1));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 1) % 157 + ".jpg",comment1));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 2) % 157 + ".jpg",comment2));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 3) % 157 + ".jpg",comment2));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 4) % 157 + ".jpg",comment3));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 5) % 157 + ".jpg",comment3));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 6) % 157 + ".jpg",comment4));
                commentPics.add(new CommentPic(USER_COMMENT_PIC_URL + (8 * i + 7) % 157 + ".jpg",comment4));

                comments.add(comment1);
                comments.add(comment2);
                comments.add(comment3);
                comments.add(comment4);
            }
        }
        commentDao.saveAll(comments);
        commentPicDao.saveAll(commentPics);
    }


    @Test
    public void addParam(){
        List<Detail> all = detailDao.findAll();
        List<Param> params = new ArrayList<>();
        int sl = season.length;
        int ml = material.length;
        int tl = type.length;
        int stl = style.length;
        int fl = fashion.length;
        if(all != null && all.size() > 0){
            for(int i = 0;i < all.size();i ++){
                params.add(new Param(randomSize(),randomLength(),season[i%sl],randomString(),material[i%ml],type[i%tl],style[i%stl],fashion[i%fl],all.get(i)));
            }
        }
        paramDao.saveAll(params);
    }

    private String randomSize(){
        int number = (int)(Math.random()*100)%6;
        String[] strings = Arrays.copyOf(size, number + 1);
        return String.join(",", strings);
    }

    private String randomLength(){
        int number = (int)(Math.random()*100)%6;
        return length[number];
    }

    @Test
    public void testInsert(){
        List<Good> goods = new ArrayList<>();
        for (int i = 0;i < 114;i ++) {
            Good good = new Good(randomString(),"http://localhost:8092/home/"+ i +".jpg",i * 10,map.get(i%3),"https://www.bilibili.com",i * 10.81);
            goods.add(good);
        }
        goodService.saveAll(goods);
    }

    @Test
    public void update(){
        goodService.updateLink("https://www.bilibili.com/");
    }

    private String randomString(){
        int randomLen = (int)(Math.random() * 100) % 11 + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < randomLen;i ++){
            int random = (int)(Math.random() * 100) % 11;
            stringBuilder.append(chars[random]);
        }
        return stringBuilder.toString();
    }

    @Test
    public void rename(){
        File file = new File("C:\\Users\\28267\\Desktop\\图包");
        File[] files = file.listFiles();
        String absolutePath = file.getAbsolutePath() + "\\";
        int index = 0;
        for (File f:files){
            File file1 = new File(absolutePath  + (index++ +112)  + f.getName().substring(f.getName().indexOf('.')));
            f.renameTo(file1);
        }
    }
}
