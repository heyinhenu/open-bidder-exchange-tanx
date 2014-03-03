package com.tagtoo.openbidder.exchange.tanx.interceptor;

import com.google.openbidder.api.bidding.BidInterceptor;
import com.google.openbidder.api.bidding.BidRequest;
import com.google.openbidder.api.bidding.BidResponse;
import com.google.openbidder.api.interceptor.InterceptorChain;
import com.google.openbidder.api.openrtb.OpenRtb;

/**
 * Created by littleq on 3/3/14.
 */
public class TestSnippetInterceptor implements BidInterceptor{
    @Override
    public void execute(InterceptorChain<BidRequest, BidResponse> chain) {
        chain.proceed();
        final String snippet = "<iframe frameBorder=\"0\" scrolling=\"no\" width=\"300\" height=\"250\" marginwidth=\"0\" marginheight=\"0\" style=\"display: visible\" src=\"http://ad.tagtoo.co/ad_g_nuclear_300x250?ad=132&pb=66&id=4#q=http%3A%2F%2Fdevelopers.google.com&p=%%SITE%%&cachebuster=%%CACHEBUSTER%%&click=%%CLICK_URL%%\"></iframe>";

        for (OpenRtb.BidResponse.SeatBid.Builder seatBid : chain.getResponse().openRtb().getSeatbidBuilderList()) {
            // for each bid
            for (OpenRtb.BidResponse.SeatBid.Bid.Builder bid: seatBid.getBidBuilderList()) {
                bid
                .setExt(OpenRtb.BidResponse.SeatBid.Bid.BidExt.newBuilder().setClickThroughUrl("http://www.google.com"))
                .setAdm(snippet);
            }
        }
    }
}