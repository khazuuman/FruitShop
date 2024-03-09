/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Paging {

    public Paging() {
    }
    private int nrpp, index, size;

    public Paging(int nrpp, int index, int size) {
        this.nrpp = nrpp;
        this.index = index;
        this.size = size;
    }

    private int totalPage, pageStart, pageEnd;

    public void calc() {
        totalPage = (size + nrpp - 1) / nrpp;
        index = index < 1 ? 1 : index;
        index = index >= totalPage ? totalPage : index;
        // ket thuc trc end
        pageStart = index - 2;
        pageStart = pageStart < 1 ? 1 : pageStart;
        pageEnd = index + 2;
        pageEnd = pageEnd >= totalPage ? totalPage : pageEnd;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

}
